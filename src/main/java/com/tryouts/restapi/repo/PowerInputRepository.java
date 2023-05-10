package com.tryouts.restapi.repo;

import com.tryouts.restapi.model.PowerInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

@Qualifier("small")
public interface PowerInputRepository extends JpaRepository<PowerInput, Long> {


}
