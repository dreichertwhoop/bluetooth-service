package com.whoop.template.client;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.whoop.apicommons.client.BaseApiClientModule;
import com.whoop.apicommons.guice.BaseGuiceModule;
import okhttp3.HttpUrl;

public class TemplateServiceClientModule extends BaseGuiceModule {

    public static final String TEMPLATE_SERVICE_URL = "TEMPLATE_SERVICE_URL";
    public static final String TEMPLATE_SERVICE_BASE_URL = "TEMPLATE_SERVICE_BASE_URL";
    public static final String WHOOP_APPLICATION_NAME = "WHOOP_APPLICATION_NAME";

    @Override
    protected void configure() {
        install(new BaseApiClientModule());
    }

    @Provides
    @Named(TEMPLATE_SERVICE_URL)
    @Singleton
    HttpUrl baseApiUrl(@Named(TEMPLATE_SERVICE_BASE_URL) String url) {
        return HttpUrl.parse(url);
    }

}
