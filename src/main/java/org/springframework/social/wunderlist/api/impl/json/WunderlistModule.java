package org.springframework.social.wunderlist.api.impl.json;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.wunderlist.api.User;

/**
 * @author Alexander Hanschke
 */
public class WunderlistModule extends SimpleModule {

    public WunderlistModule() {
        super("WunderlistModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(User.class, UserMixin.class);
    }
}
