package com.whoop.template.inttest;

import com.whoop.apicommons.integration.test.AutoDiscoveryIntegrationTestGuiceModule;
import com.whoop.apicommons.integration.test.IntegrationTestModule;
import com.whoop.auth.client.external.client.ExternalClientAuthClientModule;
import com.whoop.template.client.TemplateServiceClientModule;

public class TemplateIntegrationTestModule extends AutoDiscoveryIntegrationTestGuiceModule {

    @Override
    public void configure() {
        install(new IntegrationTestModule());
        install(new TemplateServiceClientModule());
        install(new ExternalClientAuthClientModule());
    }
}
