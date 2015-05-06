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
package org.springframework.social.wunderlist.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.wunderlist.api.WunderlistUser;
import org.springframework.social.wunderlist.api.Wunderlist;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
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
        WunderlistUser user = api.userOperations().getUser();
        values.setProviderUserId(String.valueOf(user.getId()));
        values.setDisplayName(user.getName());
    }

    @Override
    public UserProfile fetchUserProfile(Wunderlist api) {
        WunderlistUser user = api.userOperations().getUser();
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
