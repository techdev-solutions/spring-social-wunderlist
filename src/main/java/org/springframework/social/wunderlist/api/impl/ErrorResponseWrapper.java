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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class ErrorResponseWrapper {

    private JsonNode node;

    public ErrorResponseWrapper(ClientHttpResponse response) throws IOException {
        this.node = asJson(response);
    }

    public boolean isPermissionError() {
        return hasType() && "permission_error".equals(node.get("error").get("type").asText());
    }

    public boolean isError() {
        return node.get("error") != null;
    }

    public boolean hasType() {
        return isError() && node.get("error").get("type") != null;
    }

    public boolean hasMessage() {
        return isError() && node.get("error").get("message") != null;
    }

    public String getMessage() {
        if (hasMessage()) {
            return node.get("error").get("message").asText();
        }

        return null;
    }

    private JsonNode asJson(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        return mapper.readValue(response.getBody(), JsonNode.class);
    }

}
