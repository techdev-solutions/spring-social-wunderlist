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
 * Represents the recurrence of a task, i.e. 'every 2 weeks'.
 * There are pre-defined recurrences for {@link #DAILY}, {@link #WEEKLY}, {@link #BIWEEKLY}
 * and {@link #MONTHLY}.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class Recurrence {

    public static final Recurrence DAILY    = new Recurrence(1, Type.DAY);
    public static final Recurrence WEEKLY   = new Recurrence(1, Type.WEEK);
    public static final Recurrence BIWEEKLY = new Recurrence(2, Type.WEEK);
    public static final Recurrence MONTHLY  = new Recurrence(1, Type.MONTH);
    public static final Recurrence YEARLY   = new Recurrence(1, Type.YEAR);

    /**
     * Represents the supported recurrence types.
     */
    public enum Type {

        DAY, WEEK, MONTH, YEAR;

        public String value() {
            return name().toLowerCase();
        }
    }

    private final Type type;
    private final long count;

    /**
     * @param count the recurrence count.
     * @param type the recurrence type, i.e. day or week.
     */
    public Recurrence(long count, Type type) {
        Assert.notNull(type, "recurrence type must not be null");
        if (count < 1) {
            throw new IllegalArgumentException("recurrence count must be greater than 0");
        }
        this.type = type;
        this.count = count;
    }

    /**
     * @return the recurrence type.
     */
    public Type getType() {
        return type;
    }

    /**
     * @return the recurrence count.
     */
    public long getCount() {
        return count;
    }

}
