/*
 *  Copyright (c) 2023, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package io.ballerina.stdlib.test_project;

import static io.ballerina.runtime.api.utils.StringUtils.fromString;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.api.Environment;
import io.ballerina.runtime.api.values.BFuture;
import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.api.PredefinedTypes;

public class Utils {

    private Utils() {
    }

    public static BString callBMethod(Environment env, BObject client) {
        System.out.println("Inside native callBMethod method");

        BFuture future = env.getRuntime().invokeMethodAsyncSequentially(
            client, "getString", null, null, null, null, PredefinedTypes.TYPE_STRING
        );

        return (BString) getFutureResult(future);
    }

    private static Object getFutureResult(BFuture future) {
        while (!future.isDone()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // ignore
            }
        }
        return future.getResult();
    }


}
