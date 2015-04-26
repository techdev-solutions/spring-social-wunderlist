package org.springframework.social.wunderlist.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.wunderlist.api.User;
import org.springframework.social.wunderlist.api.Wunderlist;

/**
 * @author Alexander Hanschke
 */
public class WunderlistAdapter implements ApiAdapter<Wunderlist> {

    @Override
    public boolean test(Wunderlist api) {
        try {
            api.userOperations().getUser();
            return true;
        } catch (Exception cause) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(Wunderlist api, ConnectionValues values) {
        User user = api.userOperations().getUser();
        values.setProviderUserId(String.valueOf(user.getId()));
        values.setDisplayName(user.getName());
    }

    @Override
    public UserProfile fetchUserProfile(Wunderlist api) {
        User user = api.userOperations().getUser();
        return
            new UserProfileBuilder()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .build();
    }

    @Override
    public void updateStatus(Wunderlist api, String message) {
        // not supported
    }
}
