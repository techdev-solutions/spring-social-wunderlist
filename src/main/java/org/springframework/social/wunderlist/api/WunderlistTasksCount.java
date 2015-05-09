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

/**
 * Represents the number of completed and uncompleted tasks for a given list.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistTasksCount {

    private Long id;
    private Long completedCount;
    private Long uncompletedCount;
    private String type;

    /**
     * @return the id of the {@link WunderlistList} the count is based on.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the number of completed tasks.
     */
    public Long getCompletedCount() {
        return completedCount;
    }

    /**
     * @return the number of uncompleted tasks.
     */
    public Long getUncompletedCount() {
        return uncompletedCount;
    }

    /**
     * @return the type of the count (usually 'tasks_count').
     */
    public String getType() {
        return type;
    }

}
