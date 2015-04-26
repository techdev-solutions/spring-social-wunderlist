package org.springframework.social.wunderlist.api;

import java.util.List;

/**
 * @author Alexander Hanschke
 */
public interface UserOperations {

    /**
     * Retrieve the currently authenticated user
     *
     * @return the currently authenticated user
     */
    User getUser();

    /**
     * Retrieve all users the currently authenticated user can access,
     * across all lists
     *
     * @return all accessible users
     */
    List<User> getAccessibleUsers();

    /**
     * Retrieve all users the currently authenticated user can access,
     * restricted to the given list id
     *
     * @param list the id of the list to retrieve the users for
     * @return all accessible users
     */
    List<User> getAccessibleUsersForList(int list);
}
