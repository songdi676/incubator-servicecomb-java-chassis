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

package io.servicecomb.common.rest.locator;

import io.servicecomb.common.rest.definition.RestOperationMeta;
import io.servicecomb.foundation.common.RegisterManager;

/**
 * 存放具有相同path，不同httpmethod的operation
 *
 * @version  [版本号, 2017年1月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperationGroup extends RegisterManager<String, RestOperationMeta> {
    private static final String NAME = "operation group manager";

    public OperationGroup() {
        super(NAME);
    }

}
