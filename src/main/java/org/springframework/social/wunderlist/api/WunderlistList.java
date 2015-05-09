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
 * Represents a list.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistList {

    private Long id;
    private Date createdAt;
    private String title;
    private String listType;
    private String type;
    private Long revision;

    /**
     * @return the id of the list.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the date the list has been created.
     */
    public Date getCreatedAt() {
        return Dates.safeCopy(createdAt);
    }

    /**
     * @return the title of the list.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the list type of the list (usually 'list').
     */
    public String getListType() {
        return listType;
    }

    /**
     * @return the type of the list (usually 'list'):
     */
    public String getType() {
        return type;
    }

    /**
     * @return the revision of the list.
     */
    public Long getRevision() {
        return revision;
    }

}
