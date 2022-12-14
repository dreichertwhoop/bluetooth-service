package com.whoop.template.api.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.ProvisionException;
import com.whoop.apicommons.auth.external.client.TrustedExternalClientTeamId;
import com.whoop.apicommons.auth.oauth2.ValidateThirdParty;
import com.whoop.apicommons.auth.whoop.TrustedUserId;
import com.whoop.apicommons.auth.whoop.ValidateWhoopToken;
import com.whoop.auth.third.party.scopes.AvailableScopes;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("v1/auth")
public class TemplateAuthResource {

    private final Provider<Integer> trustedUserId;
    private final Provider<Integer> trustedExternalClientTeamId;

    @Inject
    public TemplateAuthResource(
        @TrustedUserId Provider<Integer> trustedUserId,
        @TrustedExternalClientTeamId Provider<Integer> trustedExternalClientTeamId
    ) {
        this.trustedUserId = trustedUserId;
        this.trustedExternalClientTeamId = trustedExternalClientTeamId;
    }

    // GET Endpoint with validateWhoop only
    @Path("user/id")
    @GET
    @ValidateWhoopToken()
    public int getAuthenticatedUserId() {
        return trustedUserId.get();
    }

    // Endpoint with one third party scope; using read:recovery as an example.
    @Path("ping/recovery")
    @GET
    @ValidateWhoopToken()
    @ValidateThirdParty(requiredScopes = AvailableScopes.READ_RECOVERY)
    public String pingRecovery() {
        trustedUserId.get();
        return "pong";
    }

    // Endpoint with multiple third party scope required to access
    @Path("ping/metrics/all")
    @GET
    @ValidateWhoopToken()
    @ValidateThirdParty(
        requiredScopes = {AvailableScopes.READ_RECOVERY, AvailableScopes.READ_SLEEP, AvailableScopes.READ_CYCLES,
                AvailableScopes.READ_HR, AvailableScopes.READ_WORKOUT}
    )
    public String pingAll() {
        trustedUserId.get();
        return "pong";
    }

    // Endpoint with multiple third party scope required to access
    @Path("ping/team")
    @GET
    @ValidateWhoopToken()
    @ValidateThirdParty(requiredScopes = AvailableScopes.READ_TEAM)
    public String pingTeam() {
        try {
            int trustedTeamId = trustedExternalClientTeamId.get();
            return "pong " + trustedTeamId;
        } catch (ProvisionException e) {
            return "pong";
        }
    }
}
