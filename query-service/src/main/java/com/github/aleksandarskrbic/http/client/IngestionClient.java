package com.github.aleksandarskrbic.http.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.github.aleksandarskrbic.domain.model.AggregateResponse;
import com.github.aleksandarskrbic.domain.model.DeviceResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;

@Path("/api/check-in/")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "ingestion-api")
public interface IngestionClient {

    @GET
    @Path("{id}/all")
    @CacheResult(cacheName = "aggregate-cache")
    Uni<AggregateResponse> checkInByLocation(@PathParam final Long id);

    @GET
    @Path("{id}/{device}")
    @CacheResult(cacheName = "device-cache")
    Uni<DeviceResponse> checkInByDevice(@PathParam final Long id, @PathParam final String device);
}
