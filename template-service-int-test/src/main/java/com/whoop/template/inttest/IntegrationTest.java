package com.whoop.template.inttest;

import com.whoop.apicommons.integration.test.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends BaseIntegrationTest {

    @Override
    public void runTest() throws Exception {
        String template = "template";
        assertThat(template).isNotNull();
        assertThat(template.length()).isEqualTo(5);
    }

    @Override
    public void cleanup() throws Exception {

    }
}
