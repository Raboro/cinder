package io.github.raboro.cinder.rest;

import io.github.raboro.cinder.rest.resources.ArchivedConferenceResource;
import io.github.raboro.cinder.rest.resources.CategoryResource;
import io.github.raboro.cinder.rest.resources.ConferenceResource;
import io.github.raboro.cinder.rest.resources.CurrencyResource;
import io.github.raboro.cinder.rest.resources.SettingsResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CurrencyResource.class);
        register(SettingsResource.class);
        register(CategoryResource.class);
        register(ConferenceResource.class);
        register(ArchivedConferenceResource.class);
    }
}
