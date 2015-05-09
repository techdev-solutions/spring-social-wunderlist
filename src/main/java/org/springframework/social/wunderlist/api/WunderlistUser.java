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
 * Represents a Wunderlist user.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistUser {

    private Long id;
    private String name;
    private String email;
    private Long revision;
    private Date createdAt;

    /**
     * @return the user's id.
     */
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the user's real name.
     */
    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the user's email address.
     */
    public String getEmail() {
        return email;
    }

    public Long getRevision() {
        return revision;
    }

    /**
     * @return the date the user has been created.
     */
    public Date getCreatedAt() {
        return Dates.safeCopy(createdAt);
    }

}
