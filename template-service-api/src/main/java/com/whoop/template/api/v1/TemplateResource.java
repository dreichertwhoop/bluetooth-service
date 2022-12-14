package com.whoop.template.api.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("v1")
public class TemplateResource {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateResource.class);

    public TemplateResource() {}

    @Path("ping")
    @POST
    public String post(String body) {
        return String.format("pong: %s", body);
    }

    @Path("time")
    @GET
    public String get() {
        return "Right now it is: " + OffsetDateTime.now(ZoneOffset.UTC);
    }

    @Path("ping")
    @GET
    public String check() {
        return "pong";
    }
}
