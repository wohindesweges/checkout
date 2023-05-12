package com.tryouts.restapi.repo;

import com.tryouts.restapi.entity.PowerInputType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PowerInputTypeRepository extends JpaRepository<PowerInputType, Long> {


}
