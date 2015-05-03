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
package org.springframework.social.wunderlist.api;

import java.util.List;

/**
 * @author Alexander Hanschke
 */
public interface TaskOperations {

    /**
     * Get a specific task
     *
     * @param taskId the id of the task to retrieve
     * @return the task for the given id
     */
    WunderlistTask getTask(long taskId);

    /**
     * Get tasks for a list
     *
     * @param listId the id of the list for which the tasks shall be fetched
     * @return all the tasks belonging the corresponding list
     */
    List<WunderlistTask> getTasks(long listId);

    /**
     * Get completed tasks for a list
     *
     * @param listId the id of the list for which the tasks shall be fetched
     * @param completed whether to fetch completed tasks or uncompleted ones
     * @return all the tasks matching the given criteria
     */
    List<WunderlistTask> getCompletedTasks(long listId, boolean completed);

    /**
     * Delete a task
     *
     * @param taskId the id of the task to be deleted
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may occur)
     */
    void deleteTask(long taskId, long revision);

}
