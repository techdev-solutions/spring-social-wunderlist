package org.springframework.social.wunderlist.api.impl.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author Alexander Hanschke
 */
abstract class UserMixin {

    @JsonProperty("created_at")
    Date createdAt;

}
