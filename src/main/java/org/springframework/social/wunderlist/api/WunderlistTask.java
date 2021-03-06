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

import java.util.Date;

/**
 * Represents a task.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistTask {

    private Long id;
    private Long assigneeId;
    private Long assignerId;
    private Date createdAt;
    private Long createdById;
    private Date dueDate;
    private Long listId;
    private Long revision;
    private boolean starred;
    private String title;

    /**
     * @return the id of the task.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the id of the {@link WunderlistUser} the task is assigned to.
     */
    public Long getAssigneeId() {
        return assigneeId;
    }

    /**
     * @return the id of the {@link WunderlistUser} the task has been assigned by.
     */
    public Long getAssignerId() {
        return assignerId;
    }

    /**
     * @return the date the task has been created.
     */
    public Date getCreatedAt() {
        return Dates.safeCopy(createdAt);
    }

    /**
     * @return the id of the {@link WunderlistUser} the task has been created by.
     */
    public Long getCreatedById() {
        return createdById;
    }

    /**
     * @return the date the task is due.
     */
    public Date getDueDate() {
        return Dates.safeCopy(dueDate);
    }

    /**
     * @return the id of the {@link WunderlistList} the task belongs to.
     */
    public Long getListId() {
        return listId;
    }

    /**
     * @return the revision of the task.
     */
    public Long getRevision() {
        return revision;
    }

    /**
     * @return whether the task is starred or not.
     */
    public boolean isStarred() {
        return starred;
    }

    /**
     * @return the title of the task.
     */
    public String getTitle() {
        return title;
    }
}
