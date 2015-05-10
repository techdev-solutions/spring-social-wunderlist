/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.wunderlist.api;

import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestOperations;

/**
 * Primary interface for all Wunderlist operations.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public interface Wunderlist extends ApiBinding {

    /**
     * Returns the portion of the Wunderlist API containing the user operations.
     *
     * @return the {@link UserOperations}.
     */
    UserOperations userOperations();

    /**
     * Returns the portion of the Wunderlist API containing the list operations.
     *
     * @return the {@link ListOperations}.
     */
    ListOperations listOperations();

    /**
     * Returns the portion of the Wunderlist API containing the task operations.
     *
     * @return the {@link TaskOperations}.
     */
    TaskOperations taskOperations();

    /**
     * Returns the underlying {@link RestOperations} object allowing for consumption of Wunderlist endpoints
     * that may not be otherwise covered by the API binding.
     * The RestOperations object returned is configured to include "X-Client-ID" and "X-Access-Token" headers on all requests.
     *
     * @return the {@link RestOperations}.
     */
    RestOperations restOperations();

}
