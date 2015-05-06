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
import org.springframework.social.wunderlist.api.WunderlistTaskData;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistTaskDataSerializer extends JsonSerializer<WunderlistTaskData> {

    @Override
    public void serialize(WunderlistTaskData value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        write(generator, value);
        generator.writeEndObject();
    }

    private void write(JsonGenerator generator, WunderlistTaskData data) throws IOException {
        generator.writeNumberField("list_id", data.getListId());
        generator.writeStringField("title", data.getTitle());
        generator.writeBooleanField("completed", data.isCompleted());
        generator.writeBooleanField("starred", data.isStarred());
        if (data.getAssignedId() != null) {
            generator.writeNumberField("assignee_id", data.getAssignedId());
        }
        if (data.getRecurrence() != null) {
            generator.writeStringField("recurrence_type", data.getRecurrence().getType().name().toLowerCase());
            generator.writeNumberField("recurrence_count", data.getRecurrence().getCount());
        }
        if (data.getDueDate() != null) {
            generator.writeStringField("due_date", new SimpleDateFormat("yyyy-MM-dd").format(data.getDueDate()));
        }
    }
}
