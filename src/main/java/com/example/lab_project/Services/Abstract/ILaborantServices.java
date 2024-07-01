package com.example.lab_project.Services.Abstract;
import com.example.lab_project.Entities.Laborant;

import java.util.List;

public interface ILaborantServices extends BaseServices<Laborant,Integer> {
public List<Laborant> getLaborantByNameOrSurname(String keyword);
}
