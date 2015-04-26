package org.springframework.social.wunderlist.api.impl;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.social.wunderlist.api.User;

import java.text.SimpleDateFormat;
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("user"), APPLICATION_JSON));

        User user = wunderlist.userOperations().getUser();
        assertNotNull(user);

        assertEquals("BENCHMARK", user.getName());
        assertEquals("benchmark@example.com", user.getEmail());
        assertEquals(Long.valueOf(6234958), user.getId());
        assertEquals(Long.valueOf(1), user.getRevision());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-08-30 10:25:58"), user.getCreatedAt());
    }

    @Test
    public void shouldFetchAccessibleUsers() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/users"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("users"), APPLICATION_JSON));

        List<User> users = wunderlist.userOperations().getAccessibleUsers();
        assertEquals(1, users.size());
    }
}
