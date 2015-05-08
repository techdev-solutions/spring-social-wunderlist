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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.social.wunderlist.api.Recurrence;
import org.springframework.social.wunderlist.api.impl.json.UpdateTaskDataSerializer;

import java.util.Date;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
@JsonSerialize(using = UpdateTaskDataSerializer.class)
public class UpdateTaskData {

    private final long taskId;
    private final long revision;

    private Boolean completed;
    private Boolean starred;

    private Long assigneeId;
    private String title;
    private Recurrence recurrence;
    private Date due;

    public UpdateTaskData(long taskId, long revision) {
        this.taskId = taskId;
        this.revision = revision;
    }

    public UpdateTaskData assignTo(long assigneeId) {
        this.assigneeId = Long.valueOf(assigneeId);
        return this;
    }

    public UpdateTaskData withTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateTaskData completed(boolean completed) {
        this.completed = Boolean.valueOf(completed);
        return this;
    }

    public UpdateTaskData every(Recurrence recurrence) {
        if (recurrence == null) {
            throw new IllegalArgumentException("recurrence must not be null");
        }
        this.recurrence = recurrence;
        return this;
    }

    public UpdateTaskData due(Date due) {
        this.due = Dates.safeCopy(due);
        return this;
    }

    public UpdateTaskData starred(boolean starred) {
        this.starred = Boolean.valueOf(starred);
        return this;
    }

    public long getTaskId() {
        return taskId;
    }

    public long getRevision() {
        return revision;
    }

    public String getTitle() {
        return title;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public Boolean isStarred() {
        return starred;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public Date getDueDate() {
        return Dates.safeCopy(due);
    }

}
