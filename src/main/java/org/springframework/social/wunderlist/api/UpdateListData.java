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

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class UpdateListData {

    private final long listId;
    private final long revision;

    private String title;

    public UpdateListData(long listId, long revision) {
        this.listId = listId;
        this.revision = revision;
    }

    public UpdateListData withTitle(String title) {
        this.title = title;
        return this;
    }

    public long getListId() {
        return listId;
    }

    public long getRevision() {
        return revision;
    }

    public String getTitle() {
        return title;
    }

}
