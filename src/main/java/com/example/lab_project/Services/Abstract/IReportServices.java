package com.example.lab_project.Services.Abstract;

import com.example.lab_project.Dto.ReportDetailsDto;
import com.example.lab_project.Entities.Laborant;
import com.example.lab_project.Entities.Patient;
import com.example.lab_project.Entities.Report;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IReportServices extends BaseServices<Report,Integer>{

   public ReportDetailsDto showReportDetails(Integer id);
   public List<Report> GetAllOrderByAsc();

   public Report createReport(Report report, MultipartFile photo) throws IOException;;
}
