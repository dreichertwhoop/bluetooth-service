package com.whoop.template.jobs;

import com.google.inject.Inject;
import com.whoop.jobs.sqs.v2.BaseSqsWorker;
import com.whoop.jobs.sqs.v2.SqsData;
import com.whoop.jobs.sqs.v2.SqsResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TemplateWorker extends BaseSqsWorker<Integer> {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateWorker.class);

    @Inject
    public TemplateWorker(SqsResources sqsResources) {
        super(sqsResources);
    }

    @Override
    public void process(SqsData<Integer> sqsData) {
        LOG.info("Got a message from the queue: {}", sqsData.getPayload());
    }

    @Override
    public Integer parseFrom(String s) throws IOException {
        return null;
    }
}
