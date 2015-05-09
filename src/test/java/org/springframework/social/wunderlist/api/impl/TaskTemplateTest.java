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
import org.springframework.social.wunderlist.api.Recurrence;
import org.springframework.social.wunderlist.api.WunderlistTask;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
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

    @Test
    public void shouldDeleteTask() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666?revision=10"))
            .andExpect(method(DELETE))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(withNoContent());

        wunderlist.taskOperations().deleteTask(666, 10);
    }

    @Test
    public void shouldCreateTask() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks"))
            .andExpect(method(POST))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.list_id", is(666)))
            .andExpect(jsonPath("$.title", is("test task")))
            .andExpect(jsonPath("$.assignee_id", is(1000)))
            .andExpect(jsonPath("$.completed", is(false)))
            .andExpect(jsonPath("$.recurrence_type", is("week")))
            .andExpect(jsonPath("$.recurrence_count", is(2)))
            .andExpect(jsonPath("$.due_date", is("2020-12-24")))
            .andExpect(jsonPath("$.starred", is(true)))
            .andRespond(with(HttpStatus.CREATED, jsonResource("task-created"), APPLICATION_JSON));

        CreateTaskData data = new CreateTaskData(666, "test task")
            .assignTo(1000)
            .completed(false)
            .due(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-24"))
            .every(new Recurrence(2, Recurrence.Type.WEEK))
            .starred(true);

        WunderlistTask task = wunderlist.taskOperations().createTask(data);
        assertNotNull(task);
    }

    @Test
    public void shouldUpdateTask() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(PATCH))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.assignee_id", is(2000)))
            .andExpect(jsonPath("$.completed", is(true)))
            .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        UpdateTaskData data = new UpdateTaskData(666, 10)
            .assignTo(2000)
            .withTitle("an updated task")
            .completed(true);

        WunderlistTask task = wunderlist.taskOperations().updateTask(data);
        assertNotNull(task);
    }

    @Test
    public void shouldRemoveAssignee() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(PATCH))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.remove[0]", is("assignee_id")))
            .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().removeAssignee(666, 10);
        assertNotNull(task);
    }

    @Test
    public void shouldRemoveDueDate() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(PATCH))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.remove[0]", is("due_date")))
            .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().removeDueDate(666, 10);
        assertNotNull(task);
    }

    @Test
    public void shouldStarTask() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(PATCH))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.starred", is(true)))
            .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().starTask(666, 10);
        assertNotNull(task);
    }

    @Test
    public void shouldUnstarTask() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(PATCH))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.starred", is(false)))
            .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().unstarTask(666, 10);
        assertNotNull(task);
    }

    @Test
    public void shouldCompleteTask() {
        server
                .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
                .andExpect(method(PATCH))
                .andExpect(header("X-Client-ID", "CLIENT_ID"))
                .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
                .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.revision", is(10)))
                .andExpect(jsonPath("$.completed", is(true)))
                .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().completeTask(666, 10);
        assertNotNull(task);
    }

    @Test
    public void shouldUncompleteTask() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/tasks/666"))
            .andExpect(method(PATCH))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andExpect(header("Content-Type", "application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.revision", is(10)))
            .andExpect(jsonPath("$.completed", is(false)))
            .andRespond(withSuccess(jsonResource("task-created"), APPLICATION_JSON));

        WunderlistTask task = wunderlist.taskOperations().uncompleteTask(666, 10);
        assertNotNull(task);
    }

}
