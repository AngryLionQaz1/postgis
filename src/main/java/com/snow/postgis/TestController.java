package com.snow.postgis;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;

@RestController
@RequestMapping("TEST")
@Slf4j
public class TestController {



    private NamedLocationService namedLocationService;
    private EntityManager entityManager;



    public TestController(NamedLocationService namedLocationService, EntityManager entityManager) {
        this.namedLocationService = namedLocationService;
        this.entityManager = entityManager;
    }

    /**
     * Used to run things right after the application has started,
     * <p>
     * This could be inserting some entities into a database or telling some other system you are online
     * or something like that. or just some simple log outputs for fun
     */
    @Transactional
    @Bean
    CommandLineRunner initialize() {
        return (x) -> {
            GeometryFactory factory = new GeometryFactory();
            Arrays.asList("City", "Church", "Waterfront", "Country")
                    .forEach(name -> {
                        namedLocationService.save(
                                new NamedLocation(
                                        name,
                                        factory.createPoint(new Coordinate(Math.random() * 50, Math.random() * 50))
                                )
                        );
                    });
        };
    }


    @GetMapping("test1")
    public void test1(){
        namedLocationService.findAll()
                .forEach(System.out::println);
    }

    @RequestMapping(
            path = "/namedlocation",
            method = RequestMethod.GET,
            produces = "application/json")
    public Iterable<NamedLocation> getRandomLocations() {
        return namedLocationService.findAll();
    }

    @RequestMapping(
            path = "/nearest",
            method = RequestMethod.GET,
            produces = "application/json",
            params = {"lat", "lon"})
    public NamedLocation getNearest(@RequestParam double lat, @RequestParam double lon) {
        Query query = entityManager.createNativeQuery(
                "SELECT id, name, point\n" +
                        "FROM named_location\n" +
                        "ORDER BY ST_Distance(point, ST_MakePoint(:lat, :lon)) ASC LIMIT 1",
                NamedLocation.class);
        return (NamedLocation) query.setParameter("lat", lat)
                .setParameter("lon", lon)
                .getSingleResult();
    }

}
