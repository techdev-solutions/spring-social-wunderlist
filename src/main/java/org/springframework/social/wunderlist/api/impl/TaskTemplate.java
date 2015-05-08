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

import org.springframework.social.wunderlist.api.TaskOperations;
import org.springframework.social.wunderlist.api.WunderlistTask;
import org.springframework.social.wunderlist.api.WunderlistTaskData;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        return restTemplate.getForObject(buildUri("tasks", "list_id", String.valueOf(listId)), WunderlistTaskList.class);
    }

    @Override
    public List<WunderlistTask> getCompletedTasks(long listId, boolean completed) {
        requireAuthorization();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.set("list_id", String.valueOf(listId));
        params.set("completed", String.valueOf(completed));

        return restTemplate.getForObject(buildUri("tasks", params), WunderlistTaskList.class);
    }

    @Override
    public void deleteTask(long taskId, long revision) {
        requireAuthorization();
        restTemplate.delete(buildUri("tasks/" + taskId, "revision", String.valueOf(revision)));
    }

    @Override
    public WunderlistTask createTask(WunderlistTaskData data) {
        requireAuthorization();
        return restTemplate.postForObject(buildUri("tasks"), data.asMap(), WunderlistTask.class);
    }
}
