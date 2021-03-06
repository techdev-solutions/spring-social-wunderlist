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
import org.springframework.social.wunderlist.api.MissingAuthenticationException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * A {@link org.springframework.web.client.ResponseErrorHandler} responsible for handling responses
 * which resulted in an HTTP 403 (Forbidden). Usually this handler will be invoked whenever
 * the client is missing the authroization headers 'X-Client-ID' and 'X-Access-Token'.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 * @see DelegatingErrorHandler
 */
class ForbiddenHandler extends DefaultResponseErrorHandler {

    @Override
    public final void handleError(ClientHttpResponse response) throws IOException {
        throw new MissingAuthenticationException("Authentication is missing (are 'X-Client-ID' and 'X-Access-Token' set?)");
    }
}
