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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.wunderlist.api.ListOperations;
import org.springframework.social.wunderlist.api.TaskOperations;
import org.springframework.social.wunderlist.api.UserOperations;
import org.springframework.social.wunderlist.api.Wunderlist;
import org.springframework.social.wunderlist.api.impl.handler.DelegatingErrorHandler;
import org.springframework.social.wunderlist.api.impl.json.WunderlistModule;
import org.springframework.social.wunderlist.connect.support.WunderlistTokenRequestInterceptor;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Hanschke
 */
public class WunderlistTemplate extends AbstractOAuth2ApiBinding implements Wunderlist {

    private UserOperations userOperations;

    private ListOperations listOperations;

    private TaskOperations taskOperations;

    public WunderlistTemplate(String accessToken, String clientId) {
        super(accessToken);

        registerInterceptors(clientId, accessToken);
        initOperations();
    }

    private void initOperations() {
        this.userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
        this.listOperations = new ListTemplate(getRestTemplate(), isAuthorized());
        this.taskOperations = new TaskTemplate(getRestTemplate(), isAuthorized());
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new WunderlistModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converter.setObjectMapper(mapper);

        return converter;
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new DelegatingErrorHandler());
    }

    private void registerInterceptors(String clientId, String accessToken) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(1);
        interceptors.add(new WunderlistTokenRequestInterceptor(clientId, accessToken));

        getRestTemplate().setInterceptors(interceptors);
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

    @Override
    public ListOperations listOperations() {
        return listOperations;
    }

    @Override
    public TaskOperations taskOperations() {
        return taskOperations;
    }

    @Override
    public RestOperations restOperations() {
        return getRestTemplate();
    }

}
