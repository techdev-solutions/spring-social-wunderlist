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

import org.springframework.social.wunderlist.api.WunderlistUser;
import org.springframework.social.wunderlist.api.UserOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Alexander Hanschke
 */
class UserTemplate extends AbstractWunderlistOperations implements UserOperations {

    private final RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public WunderlistUser getUser() {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("user"), WunderlistUser.class);
    }

    @Override
    public List<WunderlistUser> getAccessibleUsers() {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("users"), WunderlistUserList.class);
    }

    @Override
    public List<WunderlistUser> getAccessibleUsersForList(int list) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("users", "list_id", String.valueOf(list)), WunderlistUserList.class);
    }
}
