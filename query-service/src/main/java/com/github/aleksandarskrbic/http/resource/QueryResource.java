package com.github.aleksandarskrbic.http.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import com.github.aleksandarskrbic.http.client.IngestionClient;
import com.github.aleksandarskrbic.http.client.LocationClient;
import com.github.aleksandarskrbic.http.dto.DeviceReport;
import com.github.aleksandarskrbic.http.dto.FullReport;
import com.github.aleksandarskrbic.http.exception.EntityNotFound;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import io.smallrye.mutiny.Uni;

import static javax.ws.rs.core.Response.Status.*;

@Path("/api/query/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QueryResource {

    @Inject
    @RestClient
    private LocationClient locationClient;

    @Inject
    @RestClient
    private IngestionClient ingestionClient;

    @GET
    @Path("{id}/full")
    @Retry(maxRetries = 4)
    @Fallback(fallbackMethod = "fullReportFallback")
    @CircuitBreaker(requestVolumeThreshold = 4)
    public Uni<Response> getFullReport(@PathParam final Long id) throws EntityNotFound {
        return locationClient.findById(id)
            .and(ingestionClient.checkInByLocation(id))
            .onItem().apply(FullReport::new)
            .onItem().apply(Response::ok)
            .onItem().apply(ResponseBuilder::build);
    }

    @GET
    @Path("{id}/{device}")
    @Retry(maxRetries = 4)
    @Fallback(fallbackMethod = "deviceReportFallback")
    @CircuitBreaker(requestVolumeThreshold = 4)
    public Uni<Response> getReportByDevice(@PathParam final Long id, @PathParam final String device) throws EntityNotFound {
        return locationClient.findById(id)
            .and(ingestionClient.checkInByDevice(id, device.toLowerCase()))
            .onItem().apply(DeviceReport::new)
            .onItem().apply(Response::ok)
            .onItem().apply(ResponseBuilder::build);
    }

    private Uni<Response> serviceUnavailableResponse() {
        return Uni.createFrom().item(Response.status(SERVICE_UNAVAILABLE).build());
    }

    private Uni<Response> fullReportFallback(final Long id) {
        return serviceUnavailableResponse();
    }

    private Uni<Response> deviceReportFallback(final Long id, final String device) {
        return serviceUnavailableResponse();
    }
}
