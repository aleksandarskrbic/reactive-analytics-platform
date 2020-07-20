package com.github.aleksandarskrbic.domain.model;

public class AggregateResponse {

    private Long locationId;
    private long count;
    private long phoneCount;
    private long laptopCount;

    public AggregateResponse() {
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(final Long locationId) {
        this.locationId = locationId;
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
