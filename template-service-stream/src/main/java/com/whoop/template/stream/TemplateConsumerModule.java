package com.whoop.template.stream;

import com.whoop.apicommons.guice.BaseGuiceModule;
import com.whoop.streamingcommons.kafka.KafkaConsumerModule;

public class TemplateConsumerModule extends BaseGuiceModule {

    @Override
    public void configure() {
        install(new KafkaConsumerModule());

    }
}
