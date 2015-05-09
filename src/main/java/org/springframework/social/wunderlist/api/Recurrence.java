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
package org.springframework.social.wunderlist.api;

import org.springframework.util.Assert;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class Recurrence {

    public enum Type {
        DAY, WEEK, MONTH, YEAR;

        public String value() {
            return name().toLowerCase();
        }
    }

    private final Type type;
    private final long count;

    public Recurrence(long count, Type type) {
        Assert.notNull(type, "recurrence type must not be null");
        if (count < 1) {
            throw new IllegalArgumentException("recurrence count must be greater than 0");
        }
        this.type = type;
        this.count = count;
    }

    public Type getType() {
        return type;
    }

    public long getCount() {
        return count;
    }

}
