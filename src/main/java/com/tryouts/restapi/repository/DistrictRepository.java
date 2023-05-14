package com.tryouts.restapi.repository;

import com.tryouts.restapi.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DistrictRepository extends JpaRepository<District, Long> {
}
