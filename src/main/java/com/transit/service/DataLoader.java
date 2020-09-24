package com.transit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.transit.beans.City;

/**
 * @author nageshwarraokomandla;
 */
 
/**
 * Build a road map data from the text file. 
 */
@Component
public class DataLoader {

    private final Log LOG = LogFactory.getLog(getClass());

    private Map<String, City> cityMap = new HashMap<>();

    @Value("${data.file:classpath:cities.txt}")
    private String cites;

    @Autowired
    private ResourceLoader resourceLoader;


    public Map<String, City> getCityMap() {
        return cityMap;
    }

    @PostConstruct
    private void read() throws IOException {

    
        Resource resource = resourceLoader.getResource(cites);

        InputStream is;

        if (!resource.exists()) {
           
            is = new FileInputStream(new File(cites));
        } else {
          
            is = resource.getInputStream();
        }

        try (Scanner scanner = new Scanner(is)) {
			while (scanner.hasNext()) {

			    String line = scanner.nextLine();
			    if (StringUtils.isEmpty(line)) continue;

			    LOG.info(line);

			    String[] split = line.split(",");
			    String Akey = split[0].trim().toUpperCase();
			    String Bkey = split[1].trim().toUpperCase();

			    if (!Akey.equals(Bkey)) {
			        City first = cityMap.getOrDefault(Akey, City.build(Akey));
			        City second = cityMap.getOrDefault(Bkey, City.build(Bkey));

			        first.addNearby(second);
			        second.addNearby(first);

			        cityMap.put(first.getName(), first);
			        cityMap.put(second.getName(), second);
			    }
			}
		}

        LOG.info("Map: " + cityMap);
    }

    public City getCity(String name) {
        return cityMap.get(name);
    }

}
