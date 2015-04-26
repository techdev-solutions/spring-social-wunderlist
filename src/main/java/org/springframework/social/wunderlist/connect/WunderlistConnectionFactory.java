package org.springframework.social.wunderlist.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.wunderlist.api.Wunderlist;

/**
 * @author Alexander Hanschke
 */
public class WunderlistConnectionFactory extends OAuth2ConnectionFactory<Wunderlist> {

    public WunderlistConnectionFactory(String clientId, String clientSecret) {
        super("wunderlist", new WunderlistServiceProvider(clientId, clientSecret), new WunderlistAdapter());
    }
}
