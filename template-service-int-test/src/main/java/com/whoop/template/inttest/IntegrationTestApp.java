package com.whoop.template.inttest;

import com.whoop.apicommons.integration.test.IntegrationTestRunner;
import com.whoop.jobs.BaseJobRunner;

public class IntegrationTestApp extends BaseJobRunner {

    @Override
    public void registerJobs() {
        registerJob("int-test-runner", IntegrationTestRunner.class, new TemplateIntegrationTestModule());
    }

    public static void main(String... args) {
        new IntegrationTestApp().run(args);
    }
}
