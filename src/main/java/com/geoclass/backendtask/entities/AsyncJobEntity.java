package com.geoclass.backendtask.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "async_Job")
public class AsyncJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "job_id")
    @Getter @Setter
    private Long jobId;
    @Column(name = "status")
    @Getter @Setter
    private String status; // "DONE", "IN PROGRESS", "ERROR"
    @Column(name = "file_name")
    @Getter @Setter
    private String fileName;

}
