package de.blackyellow.tennis.json;

import javax.inject.Singleton;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import de.blackyellow.tennis.db.SaitenServices;

public class JerseyResourceConfig extends ResourceConfig {

    public JerseyResourceConfig() {

        // where the TestResource class is
        packages("de.blackyellow.tennis.json"); 

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new SaitenServices()).to(SaitenServices.class).in(Singleton.class);
            }
        });
    }
}
