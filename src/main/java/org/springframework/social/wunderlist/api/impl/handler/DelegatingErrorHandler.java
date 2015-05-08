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
package org.springframework.social.wunderlist.api.impl.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The primary {@link org.springframework.web.client.ResponseErrorHandler} used for
 * the library. To better handle individual errors, this component delegates error
 * handling to individual handlers.
 *
 * @author Alexander Hanschke
 * @since 1.0.0
 *
 * @see BadRequestHandler
 * @see ConflictHandler
 * @see ForbiddenHandler
 * @see GenericErrorHandler
 * @see InternalServerErrorHandler
 * @see NotFoundHandler
 * @see UnauthorizedHandler
 * @see UnprocessableEntityHandler
 */
public class DelegatingErrorHandler extends DefaultResponseErrorHandler {

    private final Map<HttpStatus, DefaultResponseErrorHandler> delegates;

    public DelegatingErrorHandler() {
        this.delegates = new HashMap<HttpStatus, DefaultResponseErrorHandler>();
        this.delegates.put(HttpStatus.CONFLICT,              new ConflictHandler());
        this.delegates.put(HttpStatus.FORBIDDEN,             new ForbiddenHandler());
        this.delegates.put(HttpStatus.NOT_FOUND,             new NotFoundHandler());
        this.delegates.put(HttpStatus.BAD_REQUEST,           new BadRequestHandler());
        this.delegates.put(HttpStatus.UNAUTHORIZED,          new UnauthorizedHandler());
        this.delegates.put(HttpStatus.UNPROCESSABLE_ENTITY,  new UnprocessableEntityHandler());
        this.delegates.put(HttpStatus.INTERNAL_SERVER_ERROR, new InternalServerErrorHandler());
    }

    @Override
    public final void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();
        delegate(status).handleError(response);
    }

    private DefaultResponseErrorHandler delegate(HttpStatus status) {
        DefaultResponseErrorHandler delegate = delegates.get(status);

        return delegate == null ? new GenericErrorHandler() : delegate;
    }

}
