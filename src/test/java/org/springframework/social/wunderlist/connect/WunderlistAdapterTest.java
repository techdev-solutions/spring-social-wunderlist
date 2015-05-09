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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.wunderlist.api.UserOperations;
import org.springframework.social.wunderlist.api.Wunderlist;
import org.springframework.social.wunderlist.api.WunderlistUser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Hanschke
 */
@RunWith(MockitoJUnitRunner.class)
public class WunderlistAdapterTest {

    @Mock
    private UserOperations userOperations;

    @Mock
    private Wunderlist wunderlist;

    private WunderlistAdapter adapter = new WunderlistAdapter();

    @Before
    public void setup() {
        Mockito.when(wunderlist.userOperations()).thenReturn(userOperations);
    }

    @Test
    public void shouldFetchProfile() {
        WunderlistUser user = new WunderlistUser();
        user.setName("Alexander Hanschke");
        user.setEmail("alexander.hanschke@techdev.de");

        Mockito.when(userOperations.getUser()).thenReturn(user);
        UserProfile profile = adapter.fetchUserProfile(wunderlist);

        assertEquals("Alexander Hanschke", profile.getName());
        assertEquals("alexander.hanschke@techdev.de", profile.getEmail());
    }

    @Test
    public void shouldFailTestWhenGetUserThrowsAnException() throws Exception {
        Mockito.when(userOperations.getUser()).thenThrow(Exception.class);

        assertThat(adapter.test(wunderlist), is(false));
    }

    @Test
    public void shouldSucceedTestWhenGetUserDoesNotThrowAnException() throws Exception {
        assertThat(adapter.test(wunderlist), is(true));
    }
}
