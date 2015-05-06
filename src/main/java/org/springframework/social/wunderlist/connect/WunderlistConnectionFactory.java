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
package org.springframework.social.wunderlist.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.wunderlist.api.Wunderlist;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistConnectionFactory extends OAuth2ConnectionFactory<Wunderlist> {

    public WunderlistConnectionFactory(String clientId, String clientSecret) {
        super("wunderlist", new WunderlistServiceProvider(clientId, clientSecret), new WunderlistAdapter());
    }
}
