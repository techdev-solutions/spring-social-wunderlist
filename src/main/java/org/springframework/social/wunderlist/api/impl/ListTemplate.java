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
import org.springframework.http.HttpMethod;
import org.springframework.social.wunderlist.api.*;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        return restTemplate.getForObject(buildUri("lists"), WunderlistLists.class);
    }

    @Override
    public WunderlistTasksCount getTasksCount(long listId) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("lists/tasks_count", "list_id", String.valueOf(listId)), WunderlistTasksCount.class);
    }

    @Override
    public WunderlistList createList(String title) {
        return createList(new CreateListData(title));
    }

    @Override
    public WunderlistList createList(CreateListData data) {
        requireAuthorization();
        Assert.notNull(data, "list data must not be null");

        return restTemplate.postForObject(buildUri("lists"), data, WunderlistList.class);
    }

    @Override
    public void deleteList(long listId, long revision) {
        requireAuthorization();
        restTemplate.delete(buildUri("lists/" + listId, "revision", String.valueOf(revision)));
    }

    @Override
    public WunderlistList publishList(long listId, long revision) {
        return updateList(new UpdateListData(listId, revision).published(true));
    }

    @Override
    public WunderlistList unpublishList(long listId, long revision) {
        return updateList(new UpdateListData(listId, revision).published(false));
    }

    @Override
    public WunderlistList updateList(UpdateListData data) {
        requireAuthorization();
        Assert.notNull(data, "list data must not be null");

        HttpEntity<UpdateListData> request = new HttpEntity(data, headers());

        HttpEntity<WunderlistList> response = restTemplate.exchange(buildUri("lists/" + data.getListId()), HttpMethod.PATCH, request, WunderlistList.class);
        return response.getBody();
    }
}
