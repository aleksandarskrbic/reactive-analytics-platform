package com.github.aleksandarskrbic.http.dto;

import io.smallrye.mutiny.tuples.Tuple2;
import com.github.aleksandarskrbic.domain.model.DeviceResponse;
import com.github.aleksandarskrbic.domain.model.Location;
import com.github.aleksandarskrbic.http.exception.EntityNotFound;

public class DeviceReport {

    private Location location;
    private long count;

    public DeviceReport() {
    }

    public DeviceReport(final Tuple2<Location, DeviceResponse> tuple) {
        if (tuple.getItem1() == null || tuple.getItem2() == null) {
            throw new EntityNotFound("Entity not found.");
        }

        location = tuple.getItem1();
        count = tuple.getItem2().getCount();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
        this.location = location;
    }

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }
}
