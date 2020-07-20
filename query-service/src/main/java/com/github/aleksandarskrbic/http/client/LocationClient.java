package com.github.aleksandarskrbic.http.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.github.aleksandarskrbic.domain.model.Location;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;

@Path("/api/locations/")
@RegisterRestClient(configKey = "location-api")
public interface LocationClient {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @CacheResult(cacheName = "location-cache")
    Uni<Location> findById(@PathParam final Long id);
}
