package com.snow.postgis;



import org.springframework.data.repository.CrudRepository;



/**
 * Created by Nikolaj Schaldemose Reibke on 4/30/17.
 */
public interface NamedLocationService extends CrudRepository<NamedLocation, Long> {

}