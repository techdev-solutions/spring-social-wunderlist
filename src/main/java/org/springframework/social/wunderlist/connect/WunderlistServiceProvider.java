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

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.wunderlist.api.Wunderlist;
import org.springframework.social.wunderlist.api.impl.WunderlistTemplate;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistServiceProvider extends AbstractOAuth2ServiceProvider<Wunderlist> {

    private final String clientId;

    public WunderlistServiceProvider(String clientId, String clientSecret) {
        super(createOAuth2Template(clientId, clientSecret));
        this.clientId = clientId;
    }

    private static OAuth2Template createOAuth2Template(String clientId, String clientSecret) {
        return new WunderlistOAuth2Template(clientId, clientSecret);
    }

    @Override
    public Wunderlist getApi(String accessToken) {
        return new WunderlistTemplate(accessToken, clientId);
    }

}
