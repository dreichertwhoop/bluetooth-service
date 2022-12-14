package com.whoop.template.api.privacy;

import com.google.inject.Singleton;
import com.whoop.apicommons.models.privacy.AccessResult;
import com.whoop.apicommons.models.privacy.ErasureResult;
import com.whoop.apicommons.models.privacy.PersonalDataDefinition;
import com.whoop.apicommons.privacy.PrivacyAdapter;

import java.util.List;

@Singleton
public class TemplateServicePrivacyAdapter implements PrivacyAdapter {

    @Override
    public List<PersonalDataDefinition> getPersonalDataDefinitions() {
        return List.of();
    }

    @Override
    public AccessResult accessPersonalData(int userId, String dataDefinitionId) {
        return AccessResult.builder().build();
    }

    @Override
    public ErasureResult erasePersonalData(int userId, String dataDefinitionId) {
        return ErasureResult.builder().build();
    }
}
