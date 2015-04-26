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
import org.springframework.social.wunderlist.api.WunderlistList;

import java.text.SimpleDateFormat;

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
public class ListTemplateTest extends AbstractWunderlistApiTest {

    @Test
    public void shouldFetchListById() throws Exception {
        server
            .expect(requestTo("https://a.wunderlist.com/api/v1/lists/666"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("list"), APPLICATION_JSON));

        WunderlistList list = wunderlist.listOperations().getList(666);
        assertNotNull(list);

        assertEquals(Long.valueOf(83526310), list.getId());
        assertEquals("Read Later", list.getTitle());
        assertEquals("list", list.getListType());
        assertEquals("list", list.getType());
        assertEquals(Long.valueOf(10), list.getRevision());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-08-30 10:29:46"), list.getCreatedAt());
    }

}
