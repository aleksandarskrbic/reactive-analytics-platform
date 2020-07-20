package com.github.aleksandarskrbic.http.dto;

import io.smallrye.mutiny.tuples.Tuple2;
import com.github.aleksandarskrbic.domain.model.AggregateResponse;
import com.github.aleksandarskrbic.domain.model.Location;
import com.github.aleksandarskrbic.http.exception.EntityNotFound;

public class FullReport {

    private Location location;
    private long count;
    private long phoneCount;
    private long laptopCount;

    public FullReport() {
    }

    public FullReport(final Tuple2<Location, AggregateResponse> tuple) {
        if (tuple.getItem1() == null || tuple.getItem2() == null) {
            throw new EntityNotFound("Entity not found.");
        }

        location = tuple.getItem1();
        count = tuple.getItem2().getCount();
        phoneCount = tuple.getItem2().getPhoneCount();
        laptopCount = tuple.getItem2().getLaptopCount();
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

    public long getPhoneCount() {
        return phoneCount;
    }

    public void setPhoneCount(final long phoneCount) {
        this.phoneCount = phoneCount;
    }

    public long getLaptopCount() {
        return laptopCount;
    }

    public void setLaptopCount(final long laptopCount) {
        this.laptopCount = laptopCount;
    }
}
