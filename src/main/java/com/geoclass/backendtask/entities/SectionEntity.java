package com.geoclass.backendtask.entities;


import jakarta.persistence.*;
import lombok.*;

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
    @Setter
    private int sectionId;
    @Column(name = "section_name")
    @Setter
    private String sectionName;


}
