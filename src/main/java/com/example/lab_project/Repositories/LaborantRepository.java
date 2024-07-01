package com.example.lab_project.Repositories;

import com.example.lab_project.Entities.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaborantRepository extends JpaRepository<Laborant,Integer> {
    @Query("SELECT l FROM Laborant l WHERE l.laborantName LIKE %:keyword% OR l.laborantSurname LIKE %:keyword%")
     List<Laborant> findByKeyword(@Param("keyword") String keyword);
}
