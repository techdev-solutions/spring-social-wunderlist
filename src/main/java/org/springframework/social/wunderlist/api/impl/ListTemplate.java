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

import org.springframework.social.wunderlist.api.ListOperations;
import org.springframework.social.wunderlist.api.WunderlistList;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alexander Hanschke
 */
class ListTemplate extends AbstractWunderlistOperations implements ListOperations {

    private final RestTemplate restTemplate;

    public ListTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public WunderlistList getList(long id) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("lists/" + id), WunderlistList.class);
    }
}
