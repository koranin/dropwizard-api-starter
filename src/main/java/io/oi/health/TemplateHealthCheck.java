package io.oi.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(final String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "Test");
        return saying.contains("TEST") ?
                Result.healthy() :
                Result.unhealthy("template doesn't include a name");
    }
}
