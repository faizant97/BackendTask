package com.geoclass.backendtask.repositories;


import com.geoclass.backendtask.entities.sectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sectionRepository extends JpaRepository<sectionEntity, Long > {
}
