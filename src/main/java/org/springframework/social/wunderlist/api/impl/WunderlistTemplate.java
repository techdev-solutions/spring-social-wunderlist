package org.springframework.social.wunderlist.api.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.wunderlist.api.UserOperations;
import org.springframework.social.wunderlist.api.Wunderlist;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Hanschke
 */
public class WunderlistTemplate extends AbstractOAuth2ApiBinding implements Wunderlist {

    private UserOperations userOperations;

    public WunderlistTemplate(String accessToken) {
        super(accessToken);
        initOperations();
    }

    private void initOperations() {
        this.userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
    }

    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        final List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(jsonMessageConverter());

        return converters;
    }

    private MappingJackson2HttpMessageConverter jsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return converter;
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

    @Override
    public RestOperations restOperations() {
        return getRestTemplate();
    }

}
