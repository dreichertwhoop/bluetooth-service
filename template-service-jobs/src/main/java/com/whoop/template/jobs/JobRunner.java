package com.whoop.template.jobs;

import com.whoop.jobs.BaseJobRunner;

public class JobRunner extends BaseJobRunner {

    @Override
    public void registerJobs() {
        registerJob("template-job", TemplateJob.class);
        registerJob("template-cron-job", TemplateCronJob.class);
        registerJob("template-worker", TemplateWorker.class);
    }

    public static void main(String... args) {
        new JobRunner().run(args);
    }
}
