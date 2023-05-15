package com.tryouts.restapi.repository;

import com.tryouts.restapi.entity.PowerInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.List;


public interface PowerInputRepository extends JpaRepository<PowerInput, Long> {

    @Query("SELECT p FROM PowerInput p where p.district.id=?1 and p.yearOfData=?2")
    List<PowerInput> findForYearAndDistrict(Long districtID, Year year);
}
