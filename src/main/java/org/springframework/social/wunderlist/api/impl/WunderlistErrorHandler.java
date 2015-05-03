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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.InternalServerErrorException;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.wunderlist.api.AuthenticationMissingException;
import org.springframework.social.wunderlist.api.NotEnoughPermissionsException;
import org.springframework.social.wunderlist.api.ValidationException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @author Alexander Hanschke
 */
class WunderlistErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();

        /*
         * UNAUTHORIZED response cannot be mapped as WunderlistError and are therefore
         * handled before deserializing the error
         */
        if (status == HttpStatus.UNAUTHORIZED) {
            throw new MissingAuthorizationException("wunderlist");
        }

        WunderlistError error = getError(response);

        switch (status) {

            case UNPROCESSABLE_ENTITY: {
                if (error.isValidationError()) {
                    throw new ValidationException(error.getAdditionalParameters().toString());
                }
                break;
            }

            case FORBIDDEN: {
                if (error.getAdditionalParameters().containsKey("authentication")) {
                    throw new AuthenticationMissingException("Authentication is missing (are 'X-Client-ID' and 'X-Access-Token' set?)");
                }
                break;
            }

            case NOT_FOUND: {
                if (error.isPermissionError()) {
                    throw new NotEnoughPermissionsException(error.getMessage());
                }
                break;
            }

            case INTERNAL_SERVER_ERROR: {
                throw new InternalServerErrorException("wunderlist", error.getMessage());
            }

            default: {
                try {
                    super.handleError(response);
                } catch (Exception cause) {
                    throw new UncategorizedApiException("wunderlist", "Error consuming Wunderlist API", cause);
                }
            }

        }
    }

    private WunderlistError getError(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        return mapper.readValue(response.getBody(), WunderlistError.class);
    }
}
