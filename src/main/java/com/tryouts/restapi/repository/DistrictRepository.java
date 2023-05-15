package com.tryouts.restapi.repository;

import com.tryouts.restapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DistrictRepository extends JpaRepository<District, Long> {
    @Query("SELECT d FROM District d WHERE d.name = ?1")
    District findByName(String districtName);


}
