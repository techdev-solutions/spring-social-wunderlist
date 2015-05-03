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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Hanschke
 */
@JsonRootName("error")
public class WunderlistError {

    @JsonProperty("type")
    private String type;
    @JsonProperty("translation_key")
    private String translationKey;
    @JsonProperty("message")
    private String message;
    @JsonProperty("title")
    private List<String> titleErrors = new ArrayList<String>();

    public String getType() {
        return type;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getTitleErrors() {
        return titleErrors;
    }

    public boolean affectsTitle() {
        return !titleErrors.isEmpty();
    }
}
