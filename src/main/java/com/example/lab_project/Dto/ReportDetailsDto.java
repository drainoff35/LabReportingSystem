package com.example.lab_project.Dto;

import com.example.lab_project.Entities.Laborant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDetailsDto {
    private String diagnosticTitle;
    private String diagnosticDescription;
    private LocalDateTime createdDate;
    private String LaborantName;
    private String LaborantSurname;
    private byte[] photo;
    private String patientName;
    private String patientSurname;
}
