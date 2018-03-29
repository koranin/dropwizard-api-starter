package io.oi;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.oi.health.TemplateHealthCheck;
import io.oi.resources.PingResource;

public class ApiStarterApplication extends Application<ApiStarterConfiguration> {
    public static void main(final String[] args) throws Exception {
        System.out.println("[ks] hello world");
        new ApiStarterApplication().run(args);


    }

    @Override
    public void run(ApiStarterConfiguration configuration, Environment environment)
            throws Exception {
        System.out.print("[ks] running");
        final PingResource pingResource = new PingResource();
        environment.jersey().register(pingResource);


        final TemplateHealthCheck healthCheck = new TemplateHealthCheck("TEST");
        environment.healthChecks().register("template", healthCheck);
    }

    @Override
    public void initialize(Bootstrap<ApiStarterConfiguration> bootstrap) {
        initializeEnvironmentVariableSubstitutor(bootstrap);
    }

    private void initializeEnvironmentVariableSubstitutor(
            Bootstrap<ApiStarterConfiguration> bootstrap
    ) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        new ResourceConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)));
    }
}
