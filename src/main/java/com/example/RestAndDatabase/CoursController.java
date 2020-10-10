package com.example.RestAndDatabase;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CoursController {

    private CoursRepository repository;
    Long id = Long.valueOf(1);

    public CoursController(CoursRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cours")
    public Iterable<Cours> cours(){
        return repository.findAll();
    }

    @GetMapping("/cour/{id}")
    public Optional<Cours> cour(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping ("/cour")
    public List<Cours> cour(@RequestParam(value="niveau") int niveau) {
        return repository.findByNiveau(niveau);
    }

    @PostMapping("/cour")
    public Cours newCours(@RequestBody Cours newCours) {
        return repository.save(newCours);
    }

    @PutMapping("/cour/{id}")
    public Cours replaceCours(@RequestBody Cours newCours, @PathVariable Long id) {

        return repository.findById(id)
                .map(cour -> {
                    cour.setTitre(newCours.getTitre());
                    cour.setDateCours(newCours.getDateCours());
                    cour.setHoraire(newCours.getHoraire());
                    cour.setNbrCavalier(newCours.getNbrCavalier());
                    cour.setNiveau(newCours.getNiveau());
                    return repository.save(cour);
                })
                .orElseGet(() -> {
                    newCours.setId(id);
                    return repository.save(newCours);
                });
    }

    @DeleteMapping("/cour/{id}")
    public void deleteCours(@PathVariable Long id) {
        repository.deleteById(id);
    }
}