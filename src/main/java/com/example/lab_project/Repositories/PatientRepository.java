package com.example.lab_project.Repositories;

import com.example.lab_project.Entities.Laborant;
import com.example.lab_project.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("SELECT p FROM Patient p WHERE p.patientName LIKE %:keyword% OR p.patientSurname LIKE %:keyword%")
    List<Patient> findByKeyword(@Param("keyword") String keyword);

}
