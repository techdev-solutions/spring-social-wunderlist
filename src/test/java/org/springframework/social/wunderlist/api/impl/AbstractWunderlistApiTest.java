package org.springframework.social.wunderlist.api.impl;

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 * @author Alexander Hanschke
 */
public abstract class AbstractWunderlistApiTest {

    protected MockRestServiceServer server;
    protected WunderlistTemplate wunderlist;

    @Before
    public void setup() {
        this.wunderlist = new WunderlistTemplate("ACCESS_TOKEN");
        this.server = MockRestServiceServer.createServer(wunderlist.getRestTemplate());
    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }

}
