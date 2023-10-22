package com.geoclass.backendtask.repositories;

import com.geoclass.backendtask.entities.AsyncJobEntity;
import com.geoclass.backendtask.entities.GeologicalClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeologicalClassRepository extends JpaRepository<GeologicalClassEntity, Long> {
    @Query(value = "SELECT class_id FROM geological_class WHERE class_code = ?1", nativeQuery = true)
    Optional<Integer> findByClassCode(String classCode);
}
