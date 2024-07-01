package com.example.lab_project.Services;

import com.example.lab_project.Dto.ReportDetailsDto;
import com.example.lab_project.Entities.Laborant;
import com.example.lab_project.Entities.Patient;
import com.example.lab_project.Entities.Report;
import com.example.lab_project.Repositories.LaborantRepository;
import com.example.lab_project.Repositories.PatientRepository;
import com.example.lab_project.Repositories.ReportRepository;
import com.example.lab_project.Services.Abstract.IReportServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ReportServices implements IReportServices {


    private final ReportRepository reportRepository;
    private final PatientRepository patientRepository;
    private final LaborantRepository laborantRepository;

    @Autowired
    public ReportServices(ReportRepository reportRepository, PatientRepository patientRepository, LaborantRepository laborantRepository) {
        this.reportRepository = reportRepository;
        this.patientRepository = patientRepository;
        this.laborantRepository = laborantRepository;
    }


    @Override
    public List<Report> GetAll() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> GetById(Integer id) {
        return reportRepository.findById(id);
    }

    @Override
    public Report Create(Report report)  {
//        Optional<Patient> tempPatient=patientRepository.findById(report.getPatientIdentityNo());
//        Optional<Laborant> tempLaborant= laborantRepository.findById(report.getLaborant().getLaborantId());
//
//        if (tempPatient.isEmpty()) {
//            throw new RuntimeException("Patient is not found.");
//        }
//
//        if (report.getLaborant() !=null && report.getLaborant().getLaborantId() != null){
//            if (tempLaborant.isPresent()){
//                report.setLaborant(tempLaborant.get());
//            } else {
//                throw new RuntimeException("Laborant is not found.");
//            }
//        }
//
//        return reportRepository.save(report);

        return reportRepository.save(report);
    }

    public Report createReport(Report report, MultipartFile photo) throws IOException {
        Optional<Patient> tempPatient=patientRepository.findById(report.getPatient().getPatientIdentityNo());
        Optional<Laborant> tempLaborant= laborantRepository.findById(report.getLaborant().getLaborantId());

        if (report.getPatient() != null && report.getPatient().getPatientIdentityNo() !=null){
            if (tempPatient.isPresent()){
                report.setPatient(tempPatient.get());
            } else{
                throw new RuntimeException("Patient not found");
            }
        }

        if (report.getLaborant() !=null && report.getLaborant().getLaborantId() != null){
            if (tempLaborant.isPresent()){
                report.setLaborant(tempLaborant.get());
            } else {
                throw new RuntimeException("Laborant is not found.");
            }
        }
        if (photo != null && !photo.isEmpty()) {
            report.setPhoto(photo.getBytes());
        }

        return reportRepository.save(report);
    }

    @Override
    public Report Update (Integer id, Report report) {

        Laborant tempLaborant=laborantRepository.findById(report.getLaborant().getLaborantId()).orElseThrow(()-> new EntityNotFoundException("Laborant is not found with id:"+report.getLaborant().getLaborantId()));

        Report temp=reportRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Report is not found with id:"+report.getReportId()));
    if (temp!=null){
        temp.setUpdatedDate(LocalDateTime.now());
        temp.setDiagnosticTitle(report.getDiagnosticTitle());
        temp.setDiagnosticDescription(report.getDiagnosticDescription());
        temp.setLaborant(tempLaborant);
    }
    return reportRepository.save(temp);
    }

    @Override
    public Report Delete(Integer id) {
        reportRepository.deleteById(id);
        return null;
    }

    @Override
    public ReportDetailsDto showReportDetails(Integer id) {
        Optional<Report> report = reportRepository.showReportDetails(id);
        if (report.isPresent()) {
            Report temp = report.get();
            ReportDetailsDto dto = new ReportDetailsDto();
            dto.setDiagnosticTitle(temp.getDiagnosticTitle());
            dto.setDiagnosticDescription(temp.getDiagnosticDescription());
            dto.setCreatedDate(temp.getCreatedDate());
            dto.setLaborantName(temp.getLaborant().getLaborantName());
            dto.setLaborantSurname(temp.getLaborant().getLaborantSurname());
            dto.setPatientName(temp.getPatient().getPatientName());
            dto.setPatientSurname(temp.getPatient().getPatientSurname());
            dto.setPhoto(temp.getPhoto());
            return dto;
        } else {
            throw new RuntimeException("Report details not found with id " + id);
        }
    }

    @Override
    public List<Report> GetAllOrderByAsc() {
        return reportRepository.GetAllOrderByAsc();
    }
}