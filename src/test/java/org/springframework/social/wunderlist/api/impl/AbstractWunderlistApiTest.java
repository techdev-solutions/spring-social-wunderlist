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

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.io.IOException;

/**
 * @author Alexander Hanschke
 */
public abstract class AbstractWunderlistApiTest {

    protected MockRestServiceServer server;
    protected WunderlistTemplate wunderlist;

    @Before
    public void setup() {
        this.wunderlist = new WunderlistTemplate("ACCESS_TOKEN", "CLIENT_ID");
        this.server = MockRestServiceServer.createServer(wunderlist.getRestTemplate());
    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }

    protected RequestMatcher authHeaders() {
        return new RequestMatcher() {
            @Override
            public void match(ClientHttpRequest request) throws IOException, AssertionError {
                MockRestRequestMatchers.header("X-Client-ID", "CLIENT_ID").match(request);
                MockRestRequestMatchers.header("X-Access-Token", "ACCESS_TOKEN").match(request);
            }
        };
    }

    protected RequestMatcher absenceOf(final String... expressions) {
        return new RequestMatcher() {
            @Override
            public void match(ClientHttpRequest request) throws IOException, AssertionError {
                for (String expression : expressions) {
                    MockRestRequestMatchers.jsonPath(expression).doesNotExist().match(request);
                }
            }
        };
    }

    protected DefaultResponseCreator withStatus(HttpStatus status, Resource body, MediaType contentType) {
        return MockRestResponseCreators.withStatus(status).body(body).contentType(contentType);
    }

}
