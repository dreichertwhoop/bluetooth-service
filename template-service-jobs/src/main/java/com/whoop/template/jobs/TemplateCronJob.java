package com.whoop.template.jobs;

import com.google.inject.Inject;
import com.whoop.jobs.cron.BaseCronWorker;
import com.whoop.jobs.cron.CronResources;

public class TemplateCronJob extends BaseCronWorker {

    @Inject
    public TemplateCronJob(CronResources cronResources) {
        super(cronResources);
    }

    @Override
    public void run() {
        // do some stuff
    }
}
