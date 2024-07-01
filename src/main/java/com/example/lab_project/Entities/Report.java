package com.example.lab_project.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    private String diagnosticTitle;
    private String diagnosticDescription;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "patient_identity_no", nullable = false)
    @JsonBackReference(value = "patient-report")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "laborant_id", nullable = false)
    @JsonBackReference(value = "laborant-report")
    private Laborant laborant;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate=null;
    }
}
