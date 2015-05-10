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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an access token request as part of the OAuth2 dance.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
class AccessTokenRequest {

    @JsonProperty("client_id")
    private String id;
    @JsonProperty("client_secret")
    private String secret;
    @JsonProperty("code")
    private String code;

    public AccessTokenRequest(String id, String secret, String code) {
        this.id = id;
        this.code = code;
        this.secret = secret;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getCode() {
        return code;
    }

}
