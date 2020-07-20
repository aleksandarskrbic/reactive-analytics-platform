package com.github.aleksandarskrbic.http.dto;

import com.github.aleksandarskrbic.kafka.model.Aggregate;

public class DeviceResponse {

    private Long locationId;
    private Long count;

    public DeviceResponse() {
    }

    public DeviceResponse(final Aggregate aggregate, final String device) {
        locationId = aggregate.getLocationId();

        if (device.equalsIgnoreCase(Aggregate.LAPTOP)) {
            count = aggregate.getLaptopCount();
        } else {
            count = aggregate.getPhoneCount();
        }
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
