package com.example.lab_project.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Laborant {
    @Id
    private Integer laborantId;
    private String laborantName;
    private String laborantSurname;

    @OneToMany(mappedBy = "laborant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "laborant-report")
    private List<Report> reports = new ArrayList<>();

}

