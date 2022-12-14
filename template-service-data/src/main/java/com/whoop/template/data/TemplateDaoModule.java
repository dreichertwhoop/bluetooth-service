package com.whoop.template.data;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.hubspot.rosetta.Rosetta;
import com.whoop.apicommons.config.ConfigModule;
import com.whoop.apicommons.guice.BaseGuiceModule;
import com.whoop.jdbi.WhoopJdbiFactory;
import com.whoop.jdbi.ported.DataSourceFactory;
import org.jdbi.v3.core.Jdbi;

public class TemplateDaoModule extends BaseGuiceModule {

    public static final String TEMPLATE_JDBI_NAME = "template.jdbi";
    private static final String FACTORY_NAME = "whoop.postgres.template-service.datasourcefactory";
    private static final String WHOOP_APPLICATION_NAME = "WHOOP_APPLICATION_NAME";

    @Override
    protected void configure() {
        install(new ConfigModule());
    }

    @Provides
    @Singleton
    @Named(FACTORY_NAME)
    DataSourceFactory provideTemplateModelDatasourceFactory(
        @Named("PGHOST") String pgHost,
        @Named("PGPORT") String pgPort,
        @Named("PGDATABASE") String pgDatabase,
        @Named("PGUSER") String pgUser,
        @Named("PGPASSWORD") String pgPassword,
        @Named(WHOOP_APPLICATION_NAME) String appName
    ) {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.setDriverClass("org.postgresql.Driver");
        dataSourceFactory.setUrl(
            String.format(
                "jdbc:postgresql://%s:%s/%s?stringtype=unspecified&ApplicationName=%s",
                pgHost,
                pgPort,
                pgDatabase,
                appName
            )
        );
        dataSourceFactory.setUser(pgUser);
        dataSourceFactory.setPassword(pgPassword);
        return dataSourceFactory;
    }

    @Provides
    @Singleton
    @Named(TEMPLATE_JDBI_NAME)
    Jdbi provideTemplateJdbi(
        WhoopJdbiFactory factory,
        MetricRegistry metricRegistry,
        @Named(FACTORY_NAME) DataSourceFactory dataSourceFactory,
        @Named(WHOOP_APPLICATION_NAME) String appName
    ) {
        Jdbi jdbi = factory.build(metricRegistry, dataSourceFactory, appName + "-postgresql");

        Rosetta.getMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
            .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

        return jdbi;
    }

    @Provides
    TemplateModelDao provideTemplateModelDao(@Named(TEMPLATE_JDBI_NAME) Jdbi jdbi) {
        return jdbi.onDemand(TemplateModelDao.class);
    }
}
