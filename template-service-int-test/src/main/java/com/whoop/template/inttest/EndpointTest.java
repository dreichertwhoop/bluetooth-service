package com.whoop.template.inttest;

import com.google.inject.Inject;
import com.whoop.apicommons.integration.test.BaseIntegrationTest;
import com.whoop.template.client.TemplateServiceClient;

import static org.assertj.core.api.Assertions.assertThat;

public class EndpointTest extends BaseIntegrationTest {

    private final TemplateServiceClient client;

    @Inject
    public EndpointTest(TemplateServiceClient client) {
        this.client = client;
    }

    @Override
    public void runTest() throws Exception {
        String getResult = client.get();
        assertThat(getResult.contains("Right now it is: "));

        String postResult = client.ping("Hello!");
        assertThat(postResult).isEqualTo("pong: Hello!");

        String checkResult = client.ping();
        assertThat(checkResult).isEqualTo("pong");
    }

    @Override
    public void cleanup() throws Exception {

    }

}
