package org.springframework.social.wunderlist.api.impl;

import org.springframework.social.wunderlist.api.User;
import org.springframework.social.wunderlist.api.UserOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Alexander Hanschke
 */
class UserTemplate extends AbstractWunderlistOperations implements UserOperations {

    private final RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate, boolean authorized) {
        super(authorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public User getUser() {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("user"), User.class);
    }

    @Override
    public List<User> getAccessibleUsers() {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("users"), UserList.class);
    }

    @Override
    public List<User> getAccessibleUsersForList(int list) {
        requireAuthorization();
        return restTemplate.getForObject(buildUri("users", "list_id", String.valueOf(list)), UserList.class);
    }
}
