package com.geoclass.backendtask.entities;

import com.geoclass.backendtask.dto.CreateSectionDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
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
//    @Column(name = "section_id")
//    @Setter
//    @Getter
//    private int sectionId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "section_id")
//    private SectionEntity sectionEntity;
}

