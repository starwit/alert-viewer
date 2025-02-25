package de.starwit.rest;

public class AlertDto {

    private String name;

    private Double[] coordinates = new Double[2];

    public AlertDto(String name, Double lat, Double lng) {
        this.name = name;
        coordinates[0] = lng;
        coordinates[1] = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return getName();
    }
}
