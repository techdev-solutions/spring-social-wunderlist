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

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistOAuth2Template extends OAuth2Template {

    private final String clientId;
    private final String clientSecret;

    public WunderlistOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate template = super.createRestTemplate();
        template.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>());

        return template;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        AccessTokenRequest request = new AccessTokenRequest(clientId, clientSecret, authorizationCode);

        Map<String, String> response = getRestTemplate().postForObject(ACCESS_TOKEN_URL, request, Map.class);
        String accessToken = response.get("access_token");

        return new AccessGrant(accessToken, null, null, null);
    }

    private static final String AUTHORIZE_URL    = "https://www.wunderlist.com/oauth/authorize";

    private static final String ACCESS_TOKEN_URL = "https://www.wunderlist.com/oauth/access_token";
}
