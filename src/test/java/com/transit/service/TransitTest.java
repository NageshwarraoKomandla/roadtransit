package com.transit.service;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.transit.beans.City;

public class TransitTest {

	
	
    @Test
    public void getNameTest() {
        City city = City.build("CHICAGO");
        Assertions.assertEquals("CHICAGO", city.getName());
    }

    @Test
    public void buildWithNeighbours() {
        City city = City.build("CHICAGO");
        city.addNearby(City.build("Boston")) .addNearby(City.build("Arling"));

        Set<City> nearby = city.getNearbyCity();
        Assertions.assertEquals(2, nearby.size());
        Assertions.assertTrue(nearby.contains(City.build("Boston")));
    }


    @Test
    public void addNearby() {
        City city = City.build("CHICAGO");
        city.addNearby(City.build("Boston")) .addNearby(City.build("NewYork"));

        Assertions.assertEquals(2, city.getNearbyCity().size());
    }

  
  


}