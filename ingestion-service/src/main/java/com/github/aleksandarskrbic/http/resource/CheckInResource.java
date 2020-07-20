package com.github.aleksandarskrbic.http.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.github.aleksandarskrbic.http.dto.AggregateResponse;
import com.github.aleksandarskrbic.http.dto.DeviceResponse;
import com.github.aleksandarskrbic.kafka.store.CheckInStore;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.smallrye.mutiny.Uni;

@Path("/api/check-in/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CheckInResource {

    @Inject
    private CheckInStore checkInStore;

    @GET
    @Path("{id}/all")
    public Uni<Response> checkInByLocation(@PathParam final Long id) {
        return checkInStore.findById(id)
            .onItem().apply(aggregate -> aggregate.map(AggregateResponse::new))
            .onItem().apply(aggregate -> aggregate.map(Response::ok).orElse(Response.noContent()))
            .onItem().apply(ResponseBuilder::build);
    }

    @GET
    @Path("{id}/{device}")
    public Uni<Response> checkInByDevice(@PathParam final Long id, @PathParam final String device) {
        return checkInStore.findById(id)
            .onItem().apply(aggregate -> aggregate.map(value -> new DeviceResponse(value, device)))
            .onItem().apply(response -> response.map(Response::ok).orElse(Response.noContent()))
            .onItem().apply(ResponseBuilder::build);
    }
}
