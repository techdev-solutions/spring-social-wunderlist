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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.wunderlist.api.ListOperations;
import org.springframework.social.wunderlist.api.WunderlistList;
import org.springframework.social.wunderlist.api.WunderlistTasksCount;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
class ListTemplate extends AbstractWunderlistOperations implements ListOperations {

    private final RestTemplate restTemplate;

    public ListTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public WunderlistList getList(long listId) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("lists/" + listId), WunderlistList.class);
    }

    @Override
    public List<WunderlistList> getLists() {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("lists"), WunderlistListList.class);
    }

    @Override
    public WunderlistTasksCount getTasksCount(long listId) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("lists/tasks_count", "list_id", String.valueOf(listId)), WunderlistTasksCount.class);
    }

    @Override
    public WunderlistList createList(String title) {
        requireAuthorization();

        Map<String, String> params = new HashMap<String, String>(1);
        params.put("title", title);

        return restTemplate.postForObject(buildUri("lists"), params, WunderlistList.class);
    }

    @Override
    public void deleteList(long listId, long revision) {
        requireAuthorization();
        restTemplate.delete(buildUri("lists/" + listId, "revision", String.valueOf(revision)));
    }

    @Override
    public WunderlistList publicizeList(long listId, boolean makePublic, long revision) {
        requireAuthorization();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));

        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("public", makePublic);
        params.put("revision", revision);

        HttpEntity request = new HttpEntity(params, headers);

        HttpEntity<WunderlistList> response = restTemplate.exchange(buildUri("lists/" + listId), HttpMethod.PATCH, request, WunderlistList.class);
        return response.getBody();
    }

    @Override
    public WunderlistList updateTitle(long listId, String newTitle, long revision) {
        requireAuthorization();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));

        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("title", newTitle);
        params.put("revision", revision);

        HttpEntity request = new HttpEntity(params, headers);

        HttpEntity<WunderlistList> response = restTemplate.exchange(buildUri("lists/" + listId), HttpMethod.PATCH, request, WunderlistList.class);
        return response.getBody();
    }
}
