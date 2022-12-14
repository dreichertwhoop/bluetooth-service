package com.whoop.template.stream;

import com.google.inject.Inject;
import com.whoop.streamingcommons.kafka.BaseKafkaConsumer;
import com.whoop.streamingcommons.kafka.KafkaConsumerResources;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateConsumer extends BaseKafkaConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateConsumer.class);

    @Inject
    public TemplateConsumer(KafkaConsumerResources kafkaConsumerResources) {
        super(kafkaConsumerResources);
    }

    @Override
    public void process(ConsumerRecord<Integer, String> consumerRecord) {
        LOG.info("Got a record");
    }
}
