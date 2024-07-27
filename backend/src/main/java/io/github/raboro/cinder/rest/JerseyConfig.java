package io.github.raboro.cinder.rest;

import io.github.raboro.cinder.rest.resources.CurrencyResource;
import io.github.raboro.cinder.rest.resources.SettingsResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CurrencyResource.class);
        register(SettingsResource.class);
    }
}
