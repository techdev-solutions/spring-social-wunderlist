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

import java.util.Date;

/**
 * Helper class for handling {@link Date} types.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public final class Dates {

    private Dates() { }

    /**
     * Creates a new date based on a given date.
     *
     * @param date the date to copy.
     * @return a new {@link Date} or {@code null} if the given date was {@code null}.
     */
    public static Date safeCopy(Date date) {
        return date == null ? null : new Date(date.getTime());
    }
}
