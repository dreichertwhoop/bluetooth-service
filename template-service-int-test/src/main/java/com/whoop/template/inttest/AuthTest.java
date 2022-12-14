package com.whoop.template.inttest;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.whoop.apicommons.client.exception.ClientException;
import com.whoop.apicommons.integration.test.BaseIntegrationTest;
import com.whoop.auth.client.external.client.ExternalClientAuthClient;
import com.whoop.template.client.TemplateServiceClient;
import okhttp3.Headers;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest extends BaseIntegrationTest {

    private static final String CLIENT_SECRET_HEADER = "CLIENT-SECRET";
    private static final String CLIENT_ID_HEADER = "CLIENT-ID";
    private final TemplateServiceClient client;
    private final ExternalClientAuthClient externalClientAuthClient;
    private final String testClientSecret;
    private final String testClientId;
    private final int testTeamId;
    private final int testUserId;

    @Inject
    public AuthTest(
        TemplateServiceClient client,
        ExternalClientAuthClient externalClientAuthClient,
        @Named("INT_TEST_CLIENT_SECRET") String clientSecret,
        @Named("INT_TEST_API_CLIENT_ID") String clientId,
        @Named("INT_TEST_EXTERNAL_CLIENT_TEAM_ID") int testTeamId,
        @Named("INT_TEST_EXTERNAL_CLIENT_TEAM_USER_ID") int testUserId
    ) {
        this.client = client;
        this.externalClientAuthClient = externalClientAuthClient;
        this.testClientSecret = clientSecret;
        this.testClientId = clientId;
        this.testTeamId = testTeamId;
        this.testUserId = testUserId;
    }

    @Override
    public void runTest() throws Exception {
        // validate standard Whoop authentication
        String authPingResponse = client.pingAuth(testUserId);
        assertThat(authPingResponse).isEqualTo(String.valueOf(testUserId));

        // validate standard Whoop auth on endpoints with multiple request annotations
        String pingRecoveryResponse = client.pingEndpointWithOneScope(testUserId);
        assertThat(pingRecoveryResponse).isEqualTo("pong");

        String pingAllMetricsResponse = client.pingEndpointWithMultipleScopes(testUserId);
        assertThat(pingAllMetricsResponse).isEqualTo("pong");

        String pingTeamResponse = client.pingTeamEndpoint(testTeamId);
        assertThat(pingTeamResponse).isEqualTo("pong");

        Headers headers =
            new Headers.Builder().set(CLIENT_ID_HEADER, testClientId)
                .set(CLIENT_SECRET_HEADER, testClientSecret)
                .build();

        // third party client should not be able to hit any endpoints without an @ValidateThirdParty annotation
        try {
            client.pingAuth(headers, testUserId);
        } catch (ClientException e) {
            assertThat(e.getStatusCode()).isEqualTo(401);
        }

        // validate third party external client can authenticate & hit endpoint(s) with valid scopes
        String clientRecoveryPingResponse = client.pingEndpointWithOneScope(headers, testUserId);
        assertThat(clientRecoveryPingResponse).isEqualTo("pong");

        String clientPingAllResponse = client.pingEndpointWithMultipleScopes(headers, testUserId);
        assertThat(clientPingAllResponse).isEqualTo("pong");

        String clientPingTeamResponse = client.pingTeamEndpoint(headers, testTeamId);
        String expectedTeamPongResponse = "pong " + testTeamId;
        assertThat(clientPingTeamResponse).isEqualTo(expectedTeamPongResponse);
    }

    @Override
    public void cleanup() throws Exception {

    }

}
