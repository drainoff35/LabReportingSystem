package com.example.lab_project.Controllers;

import com.example.lab_project.Dto.ReportDetailsDto;
import com.example.lab_project.Entities.Laborant;
import com.example.lab_project.Entities.Patient;
import com.example.lab_project.Entities.Report;
import com.example.lab_project.Services.Abstract.ILaborantServices;
import com.example.lab_project.Services.Abstract.IReportServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/report")
public class ReportController {


    private final IReportServices reportServices;
    private final ILaborantServices laborantServices;
    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public ReportController(IReportServices reportServices, ILaborantServices laborantServices, ObjectMapper jacksonObjectMapper) {
        this.reportServices = reportServices;
        this.laborantServices = laborantServices;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Report> getAllReports() {
        return reportServices.GetAllOrderByAsc();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<Report> getReportById(@PathVariable Integer id) {
        return reportServices.GetById(id);
    }

//    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
//    public ResponseEntity<Report> createReport(@RequestBody Report report) {
//        return new ResponseEntity<>(reportServices.Create(report), HttpStatus.CREATED);
//    }


    // fotoğraf yükleme işlemi bu projedeki en zorlandığım özellikti. 01.07.2024 03.56

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<String> createReport(
            @RequestPart("report") String reportJson,
            @RequestPart("photo") MultipartFile photo) {
        try {
            Report report = jacksonObjectMapper.readValue(reportJson, Report.class);
            reportServices.createReport(report, photo);
            return new ResponseEntity<>("Report created successfully", HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload photo", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> Delete(@PathVariable Integer id) {
        reportServices.Delete(id);
        return new ResponseEntity<>("Report deleted successfully",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Report> Update(@PathVariable Integer id, @RequestBody Report report) {
        return new ResponseEntity<>(reportServices.Update(id, report), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ReportDetailsDto> ShowDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(reportServices.showReportDetails(id), HttpStatus.OK);
    }
}

