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

import java.util.List;

/**
 * Primary interface for user operations.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public interface UserOperations {

    /**
     * Retrieve the currently authenticated user.
     *
     * @return the currently authenticated {@link WunderlistUser}.
     */
    WunderlistUser getUser();

    /**
     * Retrieve all users the currently authenticated user can access,
     * across all lists.
     *
     * @return a list of {@link WunderlistUser}s accessible by the current user
     */
    List<WunderlistUser> getAccessibleUsers();

    /**
     * Retrieve all users the currently authenticated user can access,
     * restricted to the given list.
     *
     * @param listId the id of the list to retrieve the users for.
     * @return a list of {@link WunderlistUser}s accessibly by the current user
     */
    List<WunderlistUser> getAccessibleUsersForList(long listId);
}
