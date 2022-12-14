package com.whoop.template.stream;


import com.whoop.streamingcommons.kafka.BaseKafkaApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends BaseKafkaApp {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    @Override
    public void registerConsumers() {
        registerConsumer("template-consumer", TemplateConsumer.class, new TemplateConsumerModule());
    }

    public static void main(String... args) {
        App app = new App();
        app.run(args);
    }
}
