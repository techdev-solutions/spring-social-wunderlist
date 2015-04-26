package org.springframework.social.wunderlist.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.wunderlist.api.Wunderlist;
import org.springframework.social.wunderlist.api.impl.WunderlistTemplate;

/**
 * @author Alexander Hanschke
 */
public class WunderlistServiceProvider extends AbstractOAuth2ServiceProvider<Wunderlist> {

    public WunderlistServiceProvider(String clientId, String clientSecret) {
        super(createOAuth2Template(clientId, clientSecret));
    }

    private static OAuth2Template createOAuth2Template(String clientId, String clientSecret) {
        OAuth2Template template = new OAuth2Template(clientId, clientSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL);
        return template;
    }

    @Override
    public Wunderlist getApi(String accessToken) {
        return new WunderlistTemplate(accessToken);
    }

    private static final String AUTHORIZE_URL    = "https://www.wunderlist.com/oauth/authorize";

    private static final String ACCESS_TOKEN_URL = "https://www.wunderlist.com/oauth/access_token";

}
