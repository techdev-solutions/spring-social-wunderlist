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
package org.springframework.social.wunderlist.api.impl;

import org.junit.Test;
import org.springframework.social.wunderlist.api.WunderlistUser;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Alexander Hanschke
 */
public class UserTemplateTest extends AbstractWunderlistApiTest {

    @Test
    public void shouldFetchUser() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(authHeaders())
            .andRespond(withSuccess(jsonResource("user"), APPLICATION_JSON));

        WunderlistUser user = wunderlist.userOperations().getUser();
        assertNotNull(user);

        assertEquals("BENCHMARK", user.getName());
        assertEquals("benchmark@example.com", user.getEmail());
        assertEquals(Long.valueOf(6234958), user.getId());
        assertEquals(Long.valueOf(1), user.getRevision());
        assertEquals(utcDateFormat().parse("2013-08-30 08:25:58"), user.getCreatedAt());
    }

    @Test
    public void shouldFetchAccessibleUsers() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/users"))
            .andExpect(method(GET))
            .andExpect(authHeaders())
            .andRespond(withSuccess(jsonResource("users-all"), APPLICATION_JSON));

        List<WunderlistUser> users = wunderlist.userOperations().getAccessibleUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void shouldFetchAccessibleUsersForList() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/users?list_id=666"))
            .andExpect(method(GET))
            .andExpect(authHeaders())
            .andRespond(withSuccess(jsonResource("users-filtered"), APPLICATION_JSON));

        List<WunderlistUser> users = wunderlist.userOperations().getAccessibleUsersForList(666);
        assertEquals(1, users.size());
    }

}
