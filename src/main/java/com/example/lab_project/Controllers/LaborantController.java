package com.example.lab_project.Controllers;

import com.example.lab_project.Entities.Laborant;
import com.example.lab_project.Entities.Report;
import com.example.lab_project.Services.Abstract.ILaborantServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laborant")
public class LaborantController {

    private final ILaborantServices laborantServices;

    @Autowired
    public LaborantController(ILaborantServices laborantServices) {
        this.laborantServices = laborantServices;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Laborant>> GetAll() {
        return new ResponseEntity<>(laborantServices.GetAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Optional<Laborant> GetById(@PathVariable Integer id) {
        return laborantServices.GetById(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Laborant>> Search(@RequestParam String search) {
        return new ResponseEntity<>(laborantServices.getLaborantByNameOrSurname(search), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Laborant> Create(@RequestBody Laborant laborant) {
        return new ResponseEntity<>(laborantServices.Create(laborant), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Laborant> Update(@PathVariable Integer id,@RequestBody Laborant laborant) {
        laborantServices.Update(id,laborant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Laborant> Delete(@PathVariable Integer id) {
    laborantServices.Delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
