package de.starwit.rest;

public class AlertDto {

private String name;

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

    @Override
    public String toString() {
        return getName();
    }

}
