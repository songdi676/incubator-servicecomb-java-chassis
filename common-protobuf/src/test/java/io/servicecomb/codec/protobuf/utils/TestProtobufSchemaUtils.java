/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.codec.protobuf.utils;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.servicecomb.common.javassist.FieldConfig;

import io.protostuff.ByteArrayInput;
import io.protostuff.Input;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufOutput;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @version  [版本号, 2017年2月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestProtobufSchemaUtils {
    public static class TestMap {
        public Map<String, String> map = new HashMap<>();

        /** 
         * <构造函数> [参数说明]
         */
        public TestMap() {
            map.put("asdf", "jjj");
        }
    }

    @Test
    public void testMap() throws Exception {
        TestMap tm = new TestMap();
        TestMap tmResult = writeThenRead(tm);
        Assert.assertEquals(tm.map, tmResult.map);

        Map<String, String> map = new HashMap<>();
        map.put("aaa", "bbb");
        testSchema(map);
    }

    @Test
    public void wrapPrimitive() throws Exception {
        Assert.assertNotNull(WrapType.ARGS_WRAP);
        Assert.assertNotNull(WrapType.NORMAL_WRAP);
        testSchema((int) 1);
        testSchema("test");
        testSchema(WrapType.ARGS_WRAP);
        Assert.assertTrue(true);
    }

    @Test
    public void wrapArray() throws Exception {
        Assert.assertNotNull(WrapType.ARGS_WRAP);
        Assert.assertNotNull(WrapType.NORMAL_WRAP);
        testArraySchema(new byte[] {0, 1, 2});
        testArraySchema(new int[] {0, 1, 2});
        testArraySchema(new String[] {"a", "b"});
        testArraySchema(new WrapType[] {WrapType.ARGS_WRAP, WrapType.NORMAL_WRAP});
        Assert.assertTrue(true);
    }

    @Test
    public void notWrap() throws Exception {
        FieldConfig expect = new FieldConfig();
        expect.setName("test");

        FieldConfig result = (FieldConfig) writeThenRead(expect);
        Assert.assertEquals(expect.getName(), result.getName());
    }

    private void testSchema(Object expect) throws Exception {
        Object result = writeThenRead(expect);
        Assert.assertEquals(expect, result);
    }

    private void testArraySchema(Object expect) throws Exception {
        Object result = writeThenRead(expect);

        int expectLen = Array.getLength(expect);
        Assert.assertEquals(expectLen, Array.getLength(result));
        for (int idx = 0; idx < expectLen; idx++) {
            Assert.assertEquals(Array.get(expect, idx), Array.get(result, idx));
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T writeThenRead(T value) throws Exception {
        WrapSchema schema = ProtobufSchemaUtils.getOrCreateSchema(value.getClass());

        byte[] bytes = toByteArray(schema, value);
        Object result = toObject(schema, bytes);
        return (T) result;
    }

    private byte[] toByteArray(WrapSchema schema, Object value) throws Exception {
        LinkedBuffer linkedBuffer = LinkedBuffer.allocate();
        ProtobufOutput output = new ProtobufOutput(linkedBuffer);

        schema.writeObject(output, value);
        return output.toByteArray();
    }

    private Object toObject(WrapSchema schema, byte[] bytes) throws Exception {
        Input input = new ByteArrayInput(bytes, false);

        return schema.readObject(input);
    }
}
