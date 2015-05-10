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
 * Primary interface for list operations.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public interface ListOperations {

    /**
     * Get a specific list.
     *
     * @param listId the id of the list to retrieve.
     * @return the {@link WunderlistList} with the given id.
     */
    WunderlistList getList(long listId);

    /**
     * Get all lists a user has permission to.
     *
     * @return a list of {@link WunderlistList}s accessible by the current user.
     */
    List<WunderlistList> getLists();

    /**
     * Get a list's tasks count.
     *
     * @param listId the id of the list to query.
     * @return the {@link WunderlistTasksCount} for the given list.
     */
    WunderlistTasksCount getTasksCount(long listId);

    /**
     * Create a list with a given title.
     *
     * @param title the title of the new list.
     * @return the created {@link WunderlistList}.
     * @see #createList(CreateListData)
     */
    WunderlistList createList(String title);

    /**
     * Create a list.
     *
     * @param data the data of the list to be created.
     * @return the created {@link WunderlistList}.
     * @see #createList(String)
     */
    WunderlistList createList(CreateListData data);

    /**
     * Delete a list permanently.
     *
     * @param listId the id of the list to be deleted.
     * @param revision the revision of the list (note that this must match with the current
     * revision of the list, otherwise a {@link ConflictException} may be thrown).
     */
    void deleteList(long listId, long revision);

    /**
     * Make a list public.
     *
     * @param listId the id of the list to be made public.
     * @param revision the revision of the list (note that this must match with the current
     * revision of the list, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistList}.
     * @see #updateList(UpdateListData)
     */
    WunderlistList publishList(long listId, long revision);

    /**
     * Make a list private.
     *
     * @param listId the id of the list to be made private.
     * @param revision the revision of the list (note that this must match with the current
     * revision of the list, otherwise a {@link ConflictException} may be thrown).
     * @return the updated {@link WunderlistList}
     * @see #updateList(UpdateListData)
     */
    WunderlistList unpublishList(long listId, long revision);

    /**
     * Update an existing list.
     *
     * @param data the changed list data.
     * @return the updated {@link WunderlistList}.
     */
    WunderlistList updateList(UpdateListData data);

}
