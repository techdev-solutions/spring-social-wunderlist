package org.springframework.social.wunderlist.api;

import java.util.Date;

/**
 * @author Alexander Hanschke
 */
public class User {

    private Long id;
    private String name;
    private String email;
    private Long revision;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getRevision() {
        return revision;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
