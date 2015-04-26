package org.springframework.social.wunderlist.api.impl;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

/**
 * @author Alexander Hanschke
 */
class AbstractWunderlistOperations {

    private final boolean authorized;

    public AbstractWunderlistOperations(boolean authorized) {
        this.authorized = authorized;
    }

    protected void requireAuthorization() {
        if (!authorized) {
            throw new MissingAuthorizationException("wunderlist");
        }
    }

    protected URI buildUri(String path) {
        return buildUri(path, EMPTY_PARAMETERS);
    }

    protected URI buildUri(String path, String name, String value) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set(name, value);
        return buildUri(path, parameters);
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(API_URL_BASE + path).queryParams(parameters).build();
    }

    private static final String API_URL_BASE = "https://a.wunderlist.com/api/v1/";

    private static final MultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();
}
