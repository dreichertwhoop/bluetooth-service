package com.whoop.template.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.whoop.apicommons.json.DateFormatUtil.OFFSET_DATE_TIME_FORMAT;

@Value.Immutable
@HubSpotStyle
public interface TemplateModelIF extends TemplateModelCore {

    UUID getId();

    @JsonFormat(pattern = OFFSET_DATE_TIME_FORMAT)
    OffsetDateTime getCreatedAt();

    @JsonFormat(pattern = OFFSET_DATE_TIME_FORMAT)
    OffsetDateTime getUpdatedAt();
}
