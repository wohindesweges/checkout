package com.tryouts.restapi.repo;

import com.tryouts.restapi.entity.District;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DistrictRepository extends JpaRepository<District, Long> {
}
