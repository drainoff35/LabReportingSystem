package com.example.lab_project.Controllers;

import com.example.lab_project.Entities.Patient;
import com.example.lab_project.Services.Abstract.IPatientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/patient")
public class PatientController {

    private IPatientServices patientServices;

    @Autowired
    public PatientController(IPatientServices patientServices) {
        this.patientServices = patientServices;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Patient>> GetAll() {
        List<Patient> patients = patientServices.GetAll();
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<Patient> GetById(@PathVariable Long id) {
        return patientServices.GetById(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Patient>> Search(@RequestParam String search) {
        return new ResponseEntity<>(patientServices.getPatientsByNameOrSurname(search), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Patient> Create(@RequestBody Patient patient) {
        return new ResponseEntity<>(patientServices.Create(patient), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> Delete(@PathVariable Long id) {
        patientServices.Delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Patient> Update(@PathVariable Long id,@RequestBody Patient patient) {
        patientServices.Update(id,patient);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
}
