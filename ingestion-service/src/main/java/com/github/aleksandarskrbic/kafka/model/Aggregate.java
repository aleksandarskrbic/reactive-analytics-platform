package com.github.aleksandarskrbic.kafka.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Aggregate {

    public static final String PHONE = "PHONE";
    public static final String LAPTOP = "LAPTOP";

    private Long locationId;
    private long count;
    private long phoneCount;
    private long laptopCount;

    public Aggregate update(final CheckInEvent event) {
        locationId = event.getLocationId();

        if (event.getDevice().equals(PHONE)) {
            phoneCount++;
        }

        if (event.getDevice().equals(LAPTOP)) {
            laptopCount++;
        }

        count++;
        return this;
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
