package com.geoclass.backendtask.entities;

import com.fasterxml.jackson.annotation.*;
import com.geoclass.backendtask.dto.CreateSectionDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "geological_class")
public class GeologicalClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    @Setter @Getter
    private int classId;
    @Column(name = "class_name")
    @Setter @Getter
    private String className;
    @Column(name = "class_code")
    @Setter @Getter
    private String classCode;


    @JsonIgnore
    @ManyToMany(mappedBy = "geologicalClass")
    private List<SectionEntity> section = new ArrayList<>();



}

