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

import org.springframework.social.wunderlist.api.impl.Dates;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Represents all the data used to create a new {@link WunderlistTask}.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class CreateTaskData {

    private final long listId;
    private final String title;

    private boolean completed;
    private boolean starred;

    private Long assigneeId;
    private Recurrence recurrence;
    private Date due;

    /**
     * @param listId the id of the {@link WunderlistList} the task belongs to.
     * @param title the title of the task.
     */
    public CreateTaskData(long listId, String title) {
        this.title = title;
        this.listId = listId;
    }

    /**
     * @param assigneeId the id of the {@link WunderlistUser} the task will be assigned to.
     * @return this {@link CreateTaskData}.
     */
    public CreateTaskData assignTo(long assigneeId) {
        this.assigneeId = Long.valueOf(assigneeId);
        return this;
    }

    /**
     * @param completed indicate whether the task is completed or not.
     * @return this {@link CreateTaskData}.
     */
    public CreateTaskData completed(boolean completed) {
        this.completed = completed;
        return this;
    }

    /**
     * @param starred indicate whether the task is starred or not.
     * @return this {@link CreateTaskData}.
     */
    public CreateTaskData starred(boolean starred) {
        this.starred = starred;
        return this;
    }

    /**
     * @param recurrence the recurrence type and count for the task, i.e. 'every 2 weeks'.
     * @return this {@link CreateTaskData}.
     */
    public CreateTaskData every(Recurrence recurrence) {
        Assert.notNull(recurrence, "task data must not be null");
        this.recurrence = recurrence;
        return this;
    }

    /**
     * @param due the date the task is due.
     * @return this {@link CreateTaskData}.
     */
    public CreateTaskData due(Date due) {
        this.due = Dates.safeCopy(due);
        return this;
    }

    /**
     * @return the id of the {@link WunderlistList} the task belongs to.
     */
    public long getListId() {
        return listId;
    }

    /**
     * @return the title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return indicates whether the task is completed or not.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @return indicates whether the task is starred or not.
     */
    public boolean isStarred() {
        return starred;
    }

    /**
     * @return the id of the {@link WunderlistUser} the task will be assigned to.
     */
    public Long getAssigneeId() {
        return assigneeId;
    }

    /**
     * @return the recurrence of the task.
     */
    public Recurrence getRecurrence() {
        return recurrence;
    }

    /**
     * @return the date the task is due.
     */
    public Date getDueDate() {
        return Dates.safeCopy(due);
    }

}
