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
package org.springframework.social.wunderlist.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Alexander Hanschke
 */
abstract class WunderlistTaskMixin {

    @JsonProperty("assignee_id")
    abstract Long getAssigneeId();

    @JsonProperty("assigner_id")
    abstract Long getAssignerId();

    @JsonProperty("created_at")
    abstract Date getCreatedAt();

    @JsonProperty("created_by_id")
    abstract Long getCreatedById();

    @JsonProperty("due_date")
    abstract Date getDueDate();

    @JsonProperty("list_id")
    abstract Long getListId();
}