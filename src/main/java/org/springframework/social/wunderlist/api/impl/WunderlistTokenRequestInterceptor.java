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
package org.springframework.social.wunderlist.api.impl;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.support.HttpRequestDecorator;

import java.io.IOException;

/**
 * A {@link ClientHttpRequestInterceptor} to ensure that requests contain the authentication
 * headers 'X-Client-ID' and 'X-Access-Token'.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
class WunderlistTokenRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String clientId;
    private final String accessToken;

    public WunderlistTokenRequestInterceptor(String clientId, String accessToken) {
        this.clientId = clientId;
        this.accessToken = accessToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequest decorator = new HttpRequestDecorator(request);
        decorator.getHeaders().set("X-Client-ID", clientId);
        decorator.getHeaders().set("X-Access-Token", accessToken);

        return execution.execute(decorator, body);
    }
}
