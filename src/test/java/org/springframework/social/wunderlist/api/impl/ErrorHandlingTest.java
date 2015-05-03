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
import org.springframework.http.HttpStatus;
import org.springframework.social.InternalServerErrorException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.wunderlist.api.MissingAuthenticationException;
import org.springframework.social.wunderlist.api.ConflictException;
import org.springframework.social.wunderlist.api.MissingParameterException;
import org.springframework.social.wunderlist.api.NotEnoughPermissionsException;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

/**
 * @author Alexander Hanschke
 */
public class ErrorHandlingTest extends AbstractWunderlistApiTest {

    @Test(expected = NotEnoughPermissionsException.class)
    public void shouldHandlePermissionError() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(with(HttpStatus.NOT_FOUND, jsonResource("permission-error"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }

    @Test(expected = InternalServerErrorException.class)
    public void shouldHandleInternalServerError() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(with(HttpStatus.INTERNAL_SERVER_ERROR, jsonResource("internal-server-error"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }

    @Test(expected = MissingAuthenticationException.class)
    public void shouldHandleMissingAuthentication() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andRespond(with(HttpStatus.FORBIDDEN, jsonResource("error-authentication-missing"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }

    @Test(expected = MissingAuthorizationException.class)
    public void shouldHandleUnauthorized() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(with(HttpStatus.UNAUTHORIZED, jsonResource("unauthorized"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }

    @Test(expected = MissingParameterException.class)
    public void shouldHandleMissingParameter() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(with(HttpStatus.BAD_REQUEST, jsonResource("error-missing-parameter"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldHandleNotFound() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(with(HttpStatus.NOT_FOUND, jsonResource("not-found"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }

    @Test(expected = ConflictException.class)
    public void shouldHandleConflict() {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/user"))
            .andExpect(method(GET))
            .andExpect(header("X-Client-ID", "CLIENT_ID"))
            .andExpect(header("X-Access-Token", "ACCESS_TOKEN"))
            .andRespond(with(HttpStatus.CONFLICT, jsonResource("conflict"), APPLICATION_JSON));

        wunderlist.userOperations().getUser();
    }
}
