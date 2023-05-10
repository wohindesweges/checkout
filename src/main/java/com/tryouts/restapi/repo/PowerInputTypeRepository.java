package com.tryouts.restapi.repo;

import com.tryouts.restapi.model.PowerInputType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("small")
public interface PowerInputTypeRepository extends JpaRepository<PowerInputType, Long> {


}
