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
 * Primary interface for task operations.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public interface TaskOperations {

    /**
     * Get a specific task.
     *
     * @param taskId the id of the task to retrieve.
     * @return the {@link WunderlistTask} with the given id.
     */
    WunderlistTask getTask(long taskId);

    /**
     * Get tasks for a list.
     *
     * @param listId the id of the list for which the tasks shall be fetched.
     * @return a list of {@link WunderlistTask}s belonging to the given list.
     */
    List<WunderlistTask> getTasks(long listId);

    /**
     * Get completed tasks for a list.
     *
     * @param listId the id of the list for which the tasks shall be fetched.
     * @return a list of completed {@link WunderlistTask}s.
     */
    List<WunderlistTask> getCompletedTasks(long listId);

    /**
     * Get uncompleted tasks for a list.
     *
     * @param listId the id of the list for which the tasks shall be fetched.
     * @return a list of uncompleted {@link WunderlistTask}s.
     */
    List<WunderlistTask> getUncompletedTasks(long listId);

    /**
     * Delete a task permanently.
     *
     * @param taskId the id of the task to be deleted.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     */
    void deleteTask(long taskId, long revision);

    /**
     * Create a task.
     *
     * @param data the data of the task to be created.
     * @return the created {@link WunderlistTask}.
     */
    WunderlistTask createTask(CreateTaskData data);

    /**
     * Update an existing task.
     *
     * @param data the changed task data.
     * @return the updated {@link WunderlistTask}.
     */
    WunderlistTask updateTask(UpdateTaskData data);

    /**
     * Mark an existing task as starred.
     *
     * @param taskId the id of the task to be marked as starred.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistTask}.
     * @see #updateTask(UpdateTaskData)
     */
    WunderlistTask starTask(long taskId, long revision);

    /**
     * Mark an existing task as not starred.
     *
     * @param taskId the id of the task to be marked as not starred.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistTask}.
     * @see #updateTask(UpdateTaskData)
     */
    WunderlistTask unstarTask(long taskId, long revision);

    /**
     * Mark an existing task as complete.
     *
     * @param taskId the id of the task to be marked as complete.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistTask}.
     * @see #updateTask(UpdateTaskData)
     */
    WunderlistTask completeTask(long taskId, long revision);

    /**
     * Mark an existing task as incomplete.
     *
     * @param taskId the id of the task to be marked as incomplete.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistTask}.
     * @see #updateTask(UpdateTaskData)
     */
    WunderlistTask uncompleteTask(long taskId, long revision);

    /**
     * Remove the assignee from an existing task.
     *
     * @param taskId the id of the task to be changed.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistTask}.
     * @see #updateTask(UpdateTaskData)
     */
    WunderlistTask removeAssignee(long taskId, long revision);

    /**
     * Remove the due date from an existing task.
     *
     * @param taskId the id of the task to be changed.
     * @param revision the revision of the task (note that this must match with the current
     * revision of the task, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistTask}.
     * @see #updateTask(UpdateTaskData)
     */
    WunderlistTask removeDueDate(long taskId, long revision);

}
