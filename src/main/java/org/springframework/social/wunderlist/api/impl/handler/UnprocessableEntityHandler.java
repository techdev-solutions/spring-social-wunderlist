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
package org.springframework.social.wunderlist.api.impl.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.wunderlist.api.ValidationException;
import org.springframework.social.wunderlist.api.impl.ErrorResponseWrapper;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
class UnprocessableEntityHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper(response);
        throw new ValidationException(wrapper.getMessage());
    }
}
