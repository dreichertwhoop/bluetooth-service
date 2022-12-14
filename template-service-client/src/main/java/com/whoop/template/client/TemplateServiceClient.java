package com.whoop.template.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.whoop.apicommons.client.BaseApiClient;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.whoop.apicommons.client.BaseApiClientModule.API_HTTP_CLIENT;
import static com.whoop.apicommons.json.JsonUtilModule.DEFAULT_MAPPER;

public class TemplateServiceClient extends BaseApiClient {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateServiceClient.class);

    private static final TypeReference<String> stringTypeReference = new TypeReference<>() {};

    private final HttpUrl baseServiceUrl;
    private final String API_VERSION = "v1";

    private final OkHttpClient httpClient;

    @Inject
    public TemplateServiceClient(
        @Named(TemplateServiceClientModule.TEMPLATE_SERVICE_URL) HttpUrl baseServiceUrl,
        @Named(API_HTTP_CLIENT) OkHttpClient httpClient,
        @Named(DEFAULT_MAPPER) ObjectMapper objectMapper,
        @Named(TemplateServiceClientModule.WHOOP_APPLICATION_NAME) String applicationName
    ) {
        super(httpClient, objectMapper);
        this.httpClient = httpClient;
        this.baseServiceUrl = baseServiceUrl.newBuilder().addPathSegments(API_VERSION + "/").build();
    }

    public String get() throws IOException {
        HttpUrl url = baseServiceUrl.newBuilder().addPathSegments("time").build();

        Request req = toGetRequest(url);

        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String ping() throws IOException {
        HttpUrl url = baseServiceUrl.newBuilder().addPathSegments("ping").build();

        Request req = toGetRequest(url);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String ping(String body) throws IOException {
        HttpUrl url = baseServiceUrl.newBuilder().addPathSegments("ping").build();

        Request req = toJsonPostRequest(url, body);

        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingAuth(int userId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("user")
                .addPathSegments("id")
                .addQueryParameter("userId", String.valueOf(userId))
                .build();

        Request req = toGetRequest(url);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingAuth(Headers headers, int userId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("user")
                .addPathSegments("id")
                .addQueryParameter("userId", String.valueOf(userId))
                .build();

        Request req = toGetRequest(url, headers);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingEndpointWithOneScope(int userId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("ping")
                .addPathSegments("recovery")
                .addQueryParameter("userId", String.valueOf(userId))
                .build();

        Request req = toGetRequest(url);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingEndpointWithOneScope(Headers headers, int userId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("ping")
                .addPathSegments("recovery")
                .addQueryParameter("userId", String.valueOf(userId))
                .build();

        Request req = toGetRequest(url, headers);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingEndpointWithMultipleScopes(int userId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("ping")
                .addPathSegments("metrics")
                .addPathSegments("all")
                .addQueryParameter("userId", String.valueOf(userId))
                .build();

        Request req = toGetRequest(url);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingEndpointWithMultipleScopes(Headers headers, int userId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("ping")
                .addPathSegments("metrics")
                .addPathSegments("all")
                .addQueryParameter("userId", String.valueOf(userId))
                .build();

        Request req = toGetRequest(url, headers);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingTeamEndpoint(int teamId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("ping")
                .addPathSegments("team")
                .addQueryParameter("teamId", String.valueOf(teamId))
                .build();

        Request req = toGetRequest(url);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }

    public String pingTeamEndpoint(Headers headers, int teamId) throws IOException {
        HttpUrl url =
            baseServiceUrl.newBuilder()
                .addPathSegments("auth")
                .addPathSegments("ping")
                .addPathSegments("team")
                .addQueryParameter("teamId", String.valueOf(teamId))
                .build();

        Request req = toGetRequest(url, headers);
        Response resp = httpClient.newCall(req).execute();
        return resp.body().string();
    }
}
