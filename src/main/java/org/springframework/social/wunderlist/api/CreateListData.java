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
 * Represents all the data used to create a new {@link WunderlistList}.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class CreateListData {

    private final String title;

    /**
     * @param title the title of the new list.
     */
    public CreateListData(String title) {
        Assert.notNull(title, "title must not be null");
        this.title = title;
    }

    /**
     * @return the title of the new list.
     */
    public String getTitle() {
        return title;
    }

}
