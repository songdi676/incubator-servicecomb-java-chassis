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

package io.servicecomb.foundation.vertx.server;

import io.vertx.core.buffer.Buffer;

/**
 * TcpBufferHandler
 *
 *
 */
public interface TcpBufferHandler {
    /**
     * handle
     * @param msgId     msgId
     * @param headerBuffer    buffer
     * @param bodyBuffer TODO
     */
    void handle(long msgId, Buffer headerBuffer, Buffer bodyBuffer);
}
