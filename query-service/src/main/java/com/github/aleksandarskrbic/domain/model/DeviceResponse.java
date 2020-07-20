package com.github.aleksandarskrbic.domain.model;

public class DeviceResponse {

    private Long locationId;
    private Long count;

    public DeviceResponse() {
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(final Long locationId) {
        this.locationId = locationId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(final Long count) {
        this.count = count;
    }
}
