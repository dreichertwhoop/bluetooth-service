package com.whoop.template.api;

import com.whoop.apicommons.guice.BaseGuiceModule;
import com.whoop.apicommons.privacy.PrivacyAdapter;
import com.whoop.template.api.privacy.TemplateServicePrivacyAdapter;
import com.whoop.template.api.v1.TemplateAuthResource;
import com.whoop.template.api.v1.TemplateResource;

public class TemplateServiceModule extends BaseGuiceModule {

    @Override
    protected void configure() {
        bind(TemplateResource.class);
        bind(TemplateAuthResource.class);
        bind(PrivacyAdapter.class).to(TemplateServicePrivacyAdapter.class);
    }
}
