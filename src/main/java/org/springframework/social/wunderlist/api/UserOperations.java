package org.springframework.social.wunderlist.api;

import java.util.List;

/**
 * @author Alexander Hanschke
 */
public interface UserOperations {

    /**
     *
     * @return
     */
    User getUser();

    /**
     *
     * @return
     */
    List<User> getAccessibleUsers();
}
