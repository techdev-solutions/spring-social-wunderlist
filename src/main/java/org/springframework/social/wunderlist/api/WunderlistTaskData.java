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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistTaskData {

    private long listId;
    private String title;
    private Long assignedId;
    private Date due;
    private boolean completed;
    private boolean starred;
    private Recurrence recurrence;

    public WunderlistTaskData(long listId, String title) {
        this.title = title;
        this.listId = listId;
    }

    public long getListId() {
        return listId;
    }

    public String getTitle() {
        return title;
    }

    public Long getAssignedId() {
        return assignedId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public Date getDueDate() {
        return Dates.safeCopy(due);
    }

    public boolean isStarred() {
        return starred;
    }

    public WunderlistTaskData assignedTo(long assigneeId) {
        this.assignedId = assigneeId;
        return this;
    }

    public WunderlistTaskData completed(boolean completed) {
        this.completed = completed;
        return this;
    }

    public WunderlistTaskData starred(boolean starred) {
        this.starred = starred;
        return this;
    }

    public WunderlistTaskData every(Recurrence recurrence) {
        this.recurrence = recurrence;
        return this;
    }

    public WunderlistTaskData due(Date due) {
        this.due = Dates.safeCopy(due);
        return this;
    }

    public Map<String, Object> asMap() {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("list_id", listId);
        map.put("title", title);
        map.put("completed", completed);
        map.put("starred", starred);
        if (assignedId != null) {
            map.put("assignee_id", assignedId);
        }
        if (recurrence != null) {
            map.put("recurrence_type", recurrence.getType().name().toLowerCase());
            map.put("recurrence_count", recurrence.getCount());
        }
        if (due != null) {
            map.put("due_date", new SimpleDateFormat("yyyy-MM-dd").format(due));
        }

        return map;
    }

}
