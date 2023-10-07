package com.accessor.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.accessor.demo.entity.LovCityEntity;


public interface CityRepository extends JpaRepository<LovCityEntity, Integer> {
	
	@Query(value = "SELECT c FROM LovCityEntity c")
	public Page<LovCityEntity> findCityName(Pageable pageable);

}
