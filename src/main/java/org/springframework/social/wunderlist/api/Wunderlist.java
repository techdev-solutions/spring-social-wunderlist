package org.springframework.social.wunderlist.api;

import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestOperations;

/**
 * @author Alexander Hanschke
 */
public interface Wunderlist extends ApiBinding {

    /**
     *
     * @return
     */
    UserOperations userOperations();

    /**
     *
     * @return
     */
    RestOperations restOperations();

}
