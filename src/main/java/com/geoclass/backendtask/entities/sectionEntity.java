package com.geoclass.backendtask.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity

@Table(name = "section")
public class sectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    @Getter @Setter
    private int id;
    @Column(name = "section_name")
    @Getter @Setter
    private String sectionName;


}
