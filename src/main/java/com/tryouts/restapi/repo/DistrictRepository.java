package com.tryouts.restapi.repo;

import com.tryouts.restapi.entity.District;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("small")
public interface DistrictRepository extends JpaRepository<District, Long> {


}
