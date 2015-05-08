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

import org.springframework.social.wunderlist.api.impl.CreateListData;
import org.springframework.social.wunderlist.api.impl.UpdateListData;

import java.util.List;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public interface ListOperations {

    /**
     * Get a specific list
     *
     * @param listId the id of the list to retrieve
     * @return the list for the given id
     */
    WunderlistList getList(long listId);

    /**
     * Get all lists a user has permission to
     *
     * @return all the lists accessible
     */
    List<WunderlistList> getLists();

    /**
     * Get a list's tasks count
     *
     * @param listId the id of the list to query
     * @return the tasks count
     */
    WunderlistTasksCount getTasksCount(long listId);

    /**
     * Create a list
     *
     * @param title the title of the new list
     * @return the created list
     */
    WunderlistList createList(String title);

    /**
     * Create a list
     *
     * @param data the data for the new list
     * @return the created list
     */
    WunderlistList createList(CreateListData data);

    /**
     * Delete a list permanently
     *
     * @param listId the id of the list to be deleted
     * @param revision the revision of the list (note that this must match with the current
     * revision of the list, otherwise a {@link ConflictException} may occur)
     */
    void deleteList(long listId, long revision);

    /**
     * Make a list public
     *
     * @param listId the id of the list to be made public (or private)
     * @param makePublic flag to indicate whether the list is made public or private
     * @param revision the revision of the list (note that this must match with the current
     * revision of the list, otherwise a {@link ConflictException} may occur)
     * @return the changed list
     */
    WunderlistList publicizeList(long listId, boolean makePublic, long revision);

    /**
     * Update an existing list
     *
     * @param data the changed list data
     * @return the updated list
     */
    WunderlistList updateList(UpdateListData data);

}
