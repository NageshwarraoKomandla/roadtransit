package com.transit.beans;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author nageshwarraokomandla;
 *
 * Class represents  City  with the connected  cities
 */

public class City {
    private String name;

    private Set<City> nearbyCity = new HashSet<>();

    private City(String name) {
        Objects.requireNonNull(name);
        this.name = name.trim().toUpperCase();
    }

    private City() {
    }

    public static City build(String name) {
        return new City(name);
    }

    @Override
    public String toString() {

       return "City:{"+"name='"+name + "'" + ", nearbyCity='" + nearbyCity  .stream() .map(City::getName).collect(Collectors.joining(",")) + "'}";
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City addNearby(City city) {
    	nearbyCity.add(city);
        return this;
    }

    public Set<City> getNearbyCity() {
        return nearbyCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
