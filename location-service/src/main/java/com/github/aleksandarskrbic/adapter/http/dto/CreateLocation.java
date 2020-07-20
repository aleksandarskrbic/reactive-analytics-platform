package com.github.aleksandarskrbic.adapter.http.dto;

public class CreateLocation {

    private String name;
    private String description;

    public CreateLocation() {
    }

    public CreateLocation(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
