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

import org.springframework.social.wunderlist.api.Recurrence;
import org.springframework.util.Assert;

import java.util.Date;

/**
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

    public CreateTaskData(long listId, String title) {
        this.title = title;
        this.listId = listId;
    }

    public CreateTaskData assignTo(long assigneeId) {
        this.assigneeId = Long.valueOf(assigneeId);
        return this;
    }

    public CreateTaskData completed(boolean completed) {
        this.completed = completed;
        return this;
    }

    public CreateTaskData starred(boolean starred) {
        this.starred = starred;
        return this;
    }

    public CreateTaskData every(Recurrence recurrence) {
        Assert.notNull(recurrence, "task data must not be null");
        this.recurrence = recurrence;
        return this;
    }

    public CreateTaskData due(Date due) {
        this.due = Dates.safeCopy(due);
        return this;
    }

    public long getListId() {
        return listId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isStarred() {
        return starred;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public Date getDueDate() {
        return Dates.safeCopy(due);
    }

}
