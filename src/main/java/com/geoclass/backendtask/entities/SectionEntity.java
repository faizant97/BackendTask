package com.geoclass.backendtask.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "section")

public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    @Setter @Getter
    private int sectionId;
    @Column(name = "section_name")
    @Setter @Getter
    private String sectionName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "assigned_section",  joinColumns = @JoinColumn(name="section_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    List<GeologicalClassEntity> geologicalClass = new ArrayList<>();

}
