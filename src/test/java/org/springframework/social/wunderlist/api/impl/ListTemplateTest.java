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

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.social.wunderlist.api.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Alexander Hanschke
 */
public class ListTemplateTest extends AbstractWunderlistApiTest {

    @Test
    public void shouldFetchListById() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/666"))
            .andExpect(method(GET))
            .andExpect(authHeaders())
            .andRespond(withSuccess(jsonResource("list"), APPLICATION_JSON));

        WunderlistList list = wunderlist.listOperations().getList(666);
        assertNotNull(list);

        assertEquals(Long.valueOf(83526310), list.getId());
        assertEquals("Read Later", list.getTitle());
        assertEquals("list", list.getListType());
        assertEquals("list", list.getType());
        assertEquals(Long.valueOf(10), list.getRevision());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN).parse("2013-08-30 10:29:46"), list.getCreatedAt());
    }

    @Test
    public void shouldFetchAllLists() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists"))
            .andExpect(method(GET))
            .andExpect(authHeaders())
            .andRespond(withSuccess(jsonResource("lists-all"), APPLICATION_JSON));

        List<WunderlistList> lists = wunderlist.listOperations().getLists();
        assertEquals(2, lists.size());
    }

    @Test
    public void shouldFetchTaskCountForList() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/tasks_count?list_id=666"))
            .andExpect(method(GET))
            .andExpect(authHeaders())
            .andRespond(withSuccess(jsonResource("tasks-count"), APPLICATION_JSON));

        WunderlistTasksCount count = wunderlist.listOperations().getTasksCount(666);
        assertNotNull(count);

        assertEquals(Long.valueOf(83526310), count.getId());
        assertEquals(Long.valueOf(100), count.getCompletedCount());
        assertEquals(Long.valueOf(200), count.getUncompletedCount());
        assertEquals("tasks_count", count.getType());
    }

    @Test
    public void shouldCreateListWithTitle() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists"))
            .andExpect(method(POST))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.title", is("a test list")))
            .andRespond(withStatus(HttpStatus.CREATED, jsonResource("list-created"), APPLICATION_JSON));

        WunderlistList list = wunderlist.listOperations().createList("a test list");
        assertNotNull(list);
    }

    @Test
    public void shouldCreateListWithData() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists"))
            .andExpect(method(POST))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.title", is("a test list")))
            .andRespond(withStatus(HttpStatus.CREATED, jsonResource("list-created"), APPLICATION_JSON));

        CreateListData data = new CreateListData("a test list");
        WunderlistList list = wunderlist.listOperations().createList(data);
        assertNotNull(list);
    }

    @Test(expected = ValidationException.class)
    public void shouldNotCreateListWithEmptyTitle() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists"))
            .andExpect(method(POST))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.title", is("")))
            .andRespond(withStatus(HttpStatus.UNPROCESSABLE_ENTITY, jsonResource("error-list-title-empty"), APPLICATION_JSON));

        CreateListData data = new CreateListData("");
        wunderlist.listOperations().createList(data);
    }

    @Test(expected = ValidationException.class)
    public void shouldNotCreateListWithOverlongTitle() {
        String title = new String(new char[256]).replace('\0', 'x');

        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists"))
            .andExpect(method(POST))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.title", is(title)))
            .andRespond(withStatus(HttpStatus.UNPROCESSABLE_ENTITY, jsonResource("error-list-title-too-long"), APPLICATION_JSON));

        CreateListData data = new CreateListData(title);
        wunderlist.listOperations().createList(data);
    }

    @Test
    public void shouldDeleteList() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/666?revision=10"))
            .andExpect(method(DELETE))
            .andExpect(authHeaders())
            .andRespond(withNoContent());

        wunderlist.listOperations().deleteList(666, 10);
    }

    @Test
    public void shouldMakeListPublic() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/666"))
            .andExpect(method(PATCH))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.public", is(true)))
            .andRespond(withSuccess(jsonResource("list-changed"), APPLICATION_JSON));

        WunderlistList list = wunderlist.listOperations().publishList(666, 10);
        assertNotNull(list);
    }

    @Test
    public void shouldMakeListPrivate() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/666"))
            .andExpect(method(PATCH))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.public", is(false)))
            .andRespond(withSuccess(jsonResource("list-changed"), APPLICATION_JSON));

        WunderlistList list = wunderlist.listOperations().unpublishList(666, 10);
        assertNotNull(list);
    }

    @Test
    public void shouldUpdateList() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/666"))
            .andExpect(method(PATCH))
            .andExpect(authHeaders())
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.title", is("a fresh new title")))
            .andRespond(withSuccess(jsonResource("list-changed"), APPLICATION_JSON));

        UpdateListData data = new UpdateListData(666, 10)
            .withTitle("a fresh new title");

        WunderlistList list = wunderlist.listOperations().updateList(data);
        assertNotNull(list);
    }

}
