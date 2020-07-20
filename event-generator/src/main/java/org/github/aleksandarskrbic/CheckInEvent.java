package org.github.aleksandarskrbic;

public class CheckInEvent {

    private Long locationId;
    private String device;

    public CheckInEvent() {
    }

    public CheckInEvent(final Long locationId, final String device) {
        this.locationId = locationId;
        this.device = device;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(final Long locationId) {
        this.locationId = locationId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(final String device) {
        this.device = device;
    }
}
