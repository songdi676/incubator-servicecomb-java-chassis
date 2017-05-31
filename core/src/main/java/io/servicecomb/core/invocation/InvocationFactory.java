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

package io.servicecomb.core.invocation;

import io.servicecomb.core.Const;
import io.servicecomb.core.Endpoint;
import io.servicecomb.core.Invocation;
import io.servicecomb.core.definition.MicroserviceMeta;
import io.servicecomb.core.definition.OperationMeta;
import io.servicecomb.core.definition.SchemaMeta;
import io.servicecomb.core.provider.consumer.ReferenceConfig;
import com.netflix.config.DynamicPropertyFactory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @version  [版本号, 2017年1月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public final class InvocationFactory {
    private static String microserviceName = getMicroserviceName();

    private InvocationFactory() {
    }

    private static String getMicroserviceName() {
        return DynamicPropertyFactory.getInstance().getStringProperty("service_description.name", null).get();
    }

    public static void setMicroserviceName(String microserviceName) {
        InvocationFactory.microserviceName = microserviceName;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param referenceConfig
     * @param operationMeta
     * @param swaggerArguments
     * @return
     */
    public static Invocation forConsumer(ReferenceConfig referenceConfig, OperationMeta operationMeta,
                                         Object[] swaggerArguments) {
        Invocation invocation = new Invocation(referenceConfig,
                operationMeta,
                swaggerArguments);
        invocation.addContext(Const.SRC_MICROSERVICE, microserviceName);
        return invocation;
    }

    /**
     * consumer端使用，schemaMeta级别的缓存，每次调用根据operationName来执行
     * @param referenceConfig
     * @param schemaMeta
     * @param operationName
     * @param swaggerArguments
     * @return
     */
    public static Invocation forConsumer(ReferenceConfig referenceConfig, SchemaMeta schemaMeta, String operationName,
            Object[] swaggerArguments) {
        OperationMeta operationMeta = schemaMeta.ensureFindOperation(operationName);
        Invocation invocation = new Invocation(referenceConfig,
                operationMeta,
                swaggerArguments);
        invocation.addContext(Const.SRC_MICROSERVICE, microserviceName);
        return invocation;
    }

    /**
     * 为tcc场景提供的快捷方式,consumer端使用
     * @param referenceConfig
     * @param operationQualifiedName
     * @param swaggerArguments
     * @return
     */
    public static Invocation forConsumer(ReferenceConfig referenceConfig, String operationQualifiedName,
            Object[] swaggerArguments) {
        MicroserviceMeta microserviceMeta = referenceConfig.getMicroserviceMeta();
        OperationMeta operationMeta = microserviceMeta.ensureFindOperation(operationQualifiedName);

        Invocation invocation = new Invocation(referenceConfig,
                operationMeta,
                swaggerArguments);
        invocation.addContext(Const.SRC_MICROSERVICE, microserviceName);
        return invocation;
    }

    /**
     * transport server收到请求时，创建invocation
     * @param endpoint
     * @param operationMeta
     * @param swaggerArguments
     * @return
     */
    public static Invocation forProvider(Endpoint endpoint,
            OperationMeta operationMeta,
            Object[] swaggerArguments) {
        return new Invocation(endpoint,
                operationMeta,
                swaggerArguments);
    }
}
