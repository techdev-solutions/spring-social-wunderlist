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
package org.springframework.social.wunderlist.api.impl.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.social.wunderlist.api.impl.CreateListData;

import java.io.IOException;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
class CreateListDataSerializer extends JsonSerializer<CreateListData> {

    @Override
    public void serialize(CreateListData data, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        write(generator, data);
        generator.writeEndObject();
    }

    private void write(JsonGenerator generator, CreateListData data) throws IOException {
        generator.writeStringField("title", data.getTitle());
    }
}
