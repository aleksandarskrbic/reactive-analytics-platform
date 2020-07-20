package com.github.aleksandarskrbic.adapter.http.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.github.aleksandarskrbic.adapter.http.dto.CreateLocation;
import com.github.aleksandarskrbic.domain.repository.LocationRepository;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.smallrye.mutiny.Uni;

@Path("/api/locations/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationResource {

    @Inject
    private LocationRepository locationRepository;

    @POST
    public Uni<Response> save(final CreateLocation location) {
        return locationRepository.save(location)
            .onItem().apply(id -> id.map(value -> Response.ok()).orElse(Response.noContent()))
            .onItem().apply(ResponseBuilder::build);
    }

    @GET
    @Path("{id}")
    public Uni<Response> findById(@PathParam final Long id) {
        return locationRepository.findById(id)
            .onItem().apply(location -> location.map(Response::ok).orElse(Response.noContent()))
            .onItem().apply(ResponseBuilder::build);
    }

    @GET
    public Uni<Response> findAll() {
         return locationRepository.findAll()
             .collectItems()
             .asList()
             .onItem().apply(locations -> locations.isEmpty()
                 ? Response.noContent()
                 : Response.ok(locations))
             .onItem().apply(ResponseBuilder::build);
    }

    @POST
    @Path("{id}")
    public Uni<Response> update(final Long id, final CreateLocation location) {
        return locationRepository.update(id, location)
            .onItem().apply(value -> value ? Response.ok() : Response.noContent())
            .onItem().apply(ResponseBuilder::build);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(final Long id) {
        return locationRepository.delete(id)
            .onItem().apply(value -> value ? Response.ok() : Response.noContent())
            .onItem().apply(ResponseBuilder::build);
    }
}
