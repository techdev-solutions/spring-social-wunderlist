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
import org.springframework.social.wunderlist.api.WunderlistTask;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Alexander Hanschke
 */
public class TaskTemplateTest extends AbstractWunderlistApiTest {

    @Test
    public void shouldFetchTaskById() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(withSuccess(jsonResource("task"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().getTask(666);
        assertNotNull(task);

        assertTrue(task.isStarred());
        assertEquals("Hello", task.getTitle());
        assertEquals(Long.valueOf(123), task.getListId());
        assertEquals(Long.valueOf(1), task.getRevision());
        assertEquals(Long.valueOf(409233670), task.getId());
        assertEquals(Long.valueOf(5432), task.getAssignerId());
        assertEquals(Long.valueOf(12345), task.getAssigneeId());
        assertEquals(Long.valueOf(6234958), task.getCreatedById());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-08-30 02:00:00"), task.getDueDate());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-08-30 10:36:13"), task.getCreatedAt());
    }

    @Test
    public void shouldFetchTasksForList() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks?list_id=666"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(withSuccess(jsonResource("tasks-all"), APPLICATION_JSON));

        List<WunderlistTask> tasks = wunderlist.taskOperations().getTasks(666);
        assertEquals(2, tasks.size());
    }

    @Test
    public void shouldFetchCompletedTasksForList() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks?list_id=666&completed=true"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(withSuccess(jsonResource("tasks-all"), APPLICATION_JSON));

        List<WunderlistTask> tasks = wunderlist.taskOperations().getCompletedTasks(666, true);
        assertEquals(2, tasks.size());
    }

    @Test
    public void shouldFetchUncompletedTasksForList() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks?list_id=666&completed=false"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(withSuccess(jsonResource("tasks-all"), APPLICATION_JSON));

        List<WunderlistTask> tasks = wunderlist.taskOperations().getCompletedTasks(666, false);
        assertEquals(2, tasks.size());
    }


}
