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
import org.springframework.test.web.client.MockRestServiceServer;

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

}
