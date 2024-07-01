package com.example.lab_project.Services.Abstract;
import com.example.lab_project.Entities.Patient;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface IPatientServices extends BaseServices<Patient,Long> {
   public List<Patient> getPatientsByNameOrSurname(String keyword);
}
