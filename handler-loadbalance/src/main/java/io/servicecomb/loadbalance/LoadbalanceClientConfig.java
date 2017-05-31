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

package io.servicecomb.loadbalance;

import com.netflix.client.config.DefaultClientConfigImpl;

/**
 * 配置转换（暂时没有配置项，待新增）
 *
 * @version  [版本号, 2016年12月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LoadbalanceClientConfig extends DefaultClientConfigImpl {
    private static final String PREFIX = "cse.loadbalance";

    public LoadbalanceClientConfig(String namespace) {
        super(namespace);
        loadProperties(PREFIX);
    }
}
