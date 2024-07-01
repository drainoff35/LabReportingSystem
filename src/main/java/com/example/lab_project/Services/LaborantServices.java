package com.example.lab_project.Services;

import com.example.lab_project.Entities.Laborant;
import com.example.lab_project.Entities.Report;
import com.example.lab_project.Repositories.LaborantRepository;
import com.example.lab_project.Services.Abstract.ILaborantServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class LaborantServices implements ILaborantServices {


    private final LaborantRepository laborantRepository;

    @Autowired
    public LaborantServices(LaborantRepository laborantRepository) {
        this.laborantRepository = laborantRepository;
    }

    @Override
    public List<Laborant> GetAll() {
        return laborantRepository.findAll();
    }

    @Override
    public Optional<Laborant> GetById(Integer id) {
        return laborantRepository.findById(id);
    }

    private boolean laborantIdentityValid(Integer id){
        return id.toString().length()==7;
    }

    @Override
    public Laborant Create(Laborant laborant) {
        if (!laborantIdentityValid(laborant.getLaborantId())){
            throw new RuntimeException("Laborant Id must be 7 digits");
        }
        if (laborant.getReports() != null){
            for (Report report : laborant.getReports())
            {
                report.setLaborant(laborant);
            }
        }
        return laborantRepository.save(laborant);
    }

    @Override
    public Laborant Update(Integer id, Laborant laborant) {
        Laborant temp=laborantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Laborant is not found with id:"+id));
        if (temp!=null){
            temp.setLaborantSurname(laborant.getLaborantSurname());
            temp.setLaborantName(laborant.getLaborantName());
        }
        return laborantRepository.save(temp);
    }

    @Override
    public Laborant Delete(Integer id) {
        laborantRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Laborant> getLaborantByNameOrSurname(String keyword) {
        return laborantRepository.findByKeyword(keyword);
    }
}
