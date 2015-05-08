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
import org.springframework.social.wunderlist.api.TaskOperations;
import org.springframework.social.wunderlist.api.WunderlistTask;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
class TaskTemplate extends AbstractWunderlistOperations implements TaskOperations {

    private final RestTemplate restTemplate;

    public TaskTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public WunderlistTask getTask(long taskId) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("tasks/" + taskId), WunderlistTask.class);
    }

    @Override
    public List<WunderlistTask> getTasks(long listId) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("tasks", "list_id", String.valueOf(listId)), WunderlistTasks.class);
    }

    @Override
    public List<WunderlistTask> getCompletedTasks(long listId, boolean completed) {
        requireAuthorization();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("list_id", String.valueOf(listId));
        params.set("completed", String.valueOf(completed));

        return restTemplate.getForObject(buildUri("tasks", params), WunderlistTasks.class);
    }

    @Override
    public void deleteTask(long taskId, long revision) {
        requireAuthorization();
        restTemplate.delete(buildUri("tasks/" + taskId, "revision", String.valueOf(revision)));
    }

    @Override
    public WunderlistTask createTask(CreateTaskData data) {
        requireAuthorization();
        Checks.notNull(data, "task data must not be null");

        return restTemplate.postForObject(buildUri("tasks"), data, WunderlistTask.class);
    }

    @Override
    public WunderlistTask updateTask(UpdateTaskData data) {
        requireAuthorization();
        Checks.notNull(data, "task data must not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));

        HttpEntity<UpdateTaskData> request = new HttpEntity(data, headers);

        HttpEntity<WunderlistTask> response = restTemplate.exchange(buildUri("tasks/" + data.getTaskId()), HttpMethod.PATCH, request, WunderlistTask.class);
        return response.getBody();
    }

    @Override
    public WunderlistTask removeAssignee(long taskId, long revision) {
        requireAuthorization();
        return removeProperty(taskId, revision, "assignee_id");
    }

    @Override
    public WunderlistTask removeDueDate(long taskId, long revision) {
        requireAuthorization();
        return removeProperty(taskId, revision, "due_date");
    }

    private WunderlistTask removeProperty(long taskId, long revision, String property) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("revision", revision);
        map.put("remove", Collections.singletonList(property).toArray(new String[1]));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));

        HttpEntity request = new HttpEntity(map, headers);

        HttpEntity<WunderlistTask> reponse = restTemplate.exchange(buildUri("tasks/" + taskId), HttpMethod.PATCH, request, WunderlistTask.class);
        return reponse.getBody();
    }
}
