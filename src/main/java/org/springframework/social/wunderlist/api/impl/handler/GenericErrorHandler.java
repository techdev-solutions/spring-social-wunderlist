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
import org.springframework.social.UncategorizedApiException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * A {@link org.springframework.web.client.ResponseErrorHandler} responsible for handling responses
 * which resulted in an error which has not been handled by any of the other handlers.
 * It simply delegates handling to the {@link DefaultResponseErrorHandler} and wraps any
 * exceptions into an {@link UncategorizedApiException}.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 * @see DelegatingErrorHandler
 */
class GenericErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        try {
            super.handleError(response);
        } catch (Exception cause) {
            throw new UncategorizedApiException("wunderlist", "Error consuming Wunderlist API", cause);
        }
    }
}
