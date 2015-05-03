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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Hanschke
 */
@JsonRootName("error")
public class WunderlistError {

    @JsonProperty("type")
    private String type;

    @JsonProperty("message")
    private String message;

    @JsonProperty("translation_key")
    private String translationKey;

    private Map<String, Object> params = new HashMap<String, Object>();

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public boolean isPermissionError() {
        return "permission_error".equals(type);
    }

    public boolean isValidationError() {
        return "validation_error".equals(type);
    }

    public Map<String, Object> getAdditionalParameters() {
        return params;
    }

    @JsonAnySetter
    protected void handleAdditionalParameters(String key, Object value) {
        params.put(key, value);
    }


}
