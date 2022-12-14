package com.whoop.template.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.whoop.apicommons.guice.BaseGuiceModule;
import com.whoop.apicommons.util.ApiInfo;
import com.whoop.apicommons.util.BaseAPIService;
import com.whoop.template.data.TemplateDaoModule;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TemplateService extends BaseAPIService<TemplateConfig> {

    private static final String CONTEXT_PATH = "/template-service";

    @Override
    public List<BaseGuiceModule> getAPIModules() {
        return Collections.singletonList(new TemplateServiceModule());
    }

    public static void main(String... args) throws Exception {
        new TemplateService().run(args);
    }

    @Override
    public void run(TemplateConfig config, Environment environment) {
        super.run(config, environment);
        environment.getApplicationContext().setContextPath(CONTEXT_PATH);
    }

    @Override
    public ApiInfo getApiInfo() {
        return ApiInfo.builder()
            .setContextPath(CONTEXT_PATH)
            .setTitle("Template Service API")
            .setDescription("An API to Bootstrap New Services")
            .build();
    }

    @Override
    protected Optional<Jdbi> getJdbiForDbMigrate() {
        Injector injector = Guice.createInjector(new TemplateDaoModule());
        Jdbi jdbi = injector.getInstance(Key.get(Jdbi.class, Names.named(TemplateDaoModule.TEMPLATE_JDBI_NAME)));
        return Optional.of(jdbi);
    }
}
