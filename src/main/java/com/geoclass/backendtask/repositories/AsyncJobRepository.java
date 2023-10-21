package com.geoclass.backendtask.repositories;

import com.geoclass.backendtask.entities.AsyncJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AsyncJobRepository extends JpaRepository<AsyncJobEntity, Long> {
    @Query(value = "SELECT * FROM async_job WHERE job_id = ?1", nativeQuery = true)
    Optional<AsyncJobEntity> findByJobId(Long emailAddress);
}
