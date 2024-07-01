package com.example.lab_project.Services;

import com.example.lab_project.Entities.Patient;
import com.example.lab_project.Repositories.PatientRepository;
import com.example.lab_project.Services.Abstract.IPatientServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PatientServices implements IPatientServices {

    private final PatientRepository patientRepository;
    @Autowired
    public PatientServices(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> GetAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> GetById(Long id) {
        return patientRepository.findById(id);
    }

    private boolean identityNumberValid(Long identityNumber) {
        return identityNumber.toString().length()==11;
    }

    @Override
    public Patient Create(Patient patient) {
        if (!identityNumberValid(patient.getPatientIdentityNo())) {
            throw new RuntimeException("Identity number must be 11 digits.");
        }
        return patientRepository.save(patient);
    }
    @Override
    public Patient Update(Long id, Patient patient) {
        Patient temp=patientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Patient is not found with id: "+id));
        if(temp!=null) {
            temp.setPatientName(patient.getPatientName());
            temp.setPatientSurname(patient.getPatientSurname());
        }
        return patientRepository.save(temp);
    }

    @Override
    public Patient Delete(Long id) {
        patientRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Patient> getPatientsByNameOrSurname(String keyword) {
        return patientRepository.findByKeyword(keyword);
    }


}
