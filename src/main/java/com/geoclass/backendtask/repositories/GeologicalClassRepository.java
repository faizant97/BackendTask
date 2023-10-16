package com.geoclass.backendtask.repositories;

import com.geoclass.backendtask.entities.GeologicalClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeologicalClassRepository extends JpaRepository<GeologicalClassEntity, Long> {

}
