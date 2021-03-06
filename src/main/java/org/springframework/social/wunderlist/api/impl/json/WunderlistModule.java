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

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.wunderlist.api.*;

/**
 * @author Alexander Hanschke
 * @since 1.0.0
 */
public class WunderlistModule extends SimpleModule {

    public WunderlistModule() {
        super("WunderlistModule");
        init();
    }

    private void init() {
        addSerializer(CreateListData.class, new CreateListDataSerializer());
        addSerializer(UpdateListData.class, new UpdateListDataSerializer());
        addSerializer(CreateTaskData.class, new CreateTaskDataSerializer());
        addSerializer(UpdateTaskData.class, new UpdateTaskDataSerializer());

        setMixInAnnotation(WunderlistUser.class, WunderlistUserMixin.class);
        setMixInAnnotation(WunderlistList.class, WunderlistListMixin.class);
        setMixInAnnotation(WunderlistTask.class, WunderlistTaskMixin.class);
        setMixInAnnotation(WunderlistTasksCount.class, WunderlistTasksCountMixin.class);
    }
}
