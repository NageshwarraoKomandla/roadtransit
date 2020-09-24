package com.transit.service;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.transit.beans.City;

/**
 * @author nageshwarraokomandla;
 */
 

@Component
public class Transit {

	private static final Log LOG = LogFactory.getLog(Transit.class);

	private Transit() {
	}

		
	public  boolean commute(City origin, City destination) {


		LOG.info("inside commute(): origin:-> " + origin + " destination:-> " + destination);

		
		if (origin.equals(destination)) 	return true;

		if (origin.getNearbyCity().contains(destination))  return true;

		Set<City> visited = new HashSet<>(Collections.singleton(origin));

		Deque<City> bucketlist = new ArrayDeque<>(origin.getNearbyCity());

		while (!bucketlist.isEmpty()) {

			City city = bucketlist.getLast();

			if (city.equals(destination)) 	return true;

			if (!visited.contains(city)) {
				visited.add(city);
				bucketlist.addAll(city.getNearbyCity());
				bucketlist.removeAll(visited);

			} else {

				bucketlist.removeAll(Collections.singleton(city));
			}
		}

		return false;
	}
}
