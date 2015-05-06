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

import java.util.Date;

/**
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

    public Long getId() {
        return id;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public Long getAssignerId() {
        return assignerId;
    }

    public Date getCreatedAt() {
        return Dates.safeCopy(createdAt);
    }

    public Long getCreatedById() {
        return createdById;
    }

    public Date getDueDate() {
        return Dates.safeCopy(dueDate);
    }

    public Long getListId() {
        return listId;
    }

    public Long getRevision() {
        return revision;
    }

    public boolean isStarred() {
        return starred;
    }

    public String getTitle() {
        return title;
    }
}
