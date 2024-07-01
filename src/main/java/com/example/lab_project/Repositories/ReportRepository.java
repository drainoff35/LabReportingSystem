package com.example.lab_project.Repositories;

import com.example.lab_project.Dto.ReportDetailsDto;
import com.example.lab_project.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {

    @Query("SELECT r FROM Report r WHERE r.reportId = :id")
    Optional<Report> showReportDetails(@Param("id") Integer id);

    @Query("SELECT r from Report r order by r.createdDate asc ")
    List<Report> GetAllOrderByAsc();


}
