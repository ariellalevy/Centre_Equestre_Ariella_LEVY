package com.example.RestAndDatabase;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public void newCours(@RequestBody Cours newCours, HttpServletResponse response) throws IOException {
        repository.save(newCours);
        response.getWriter().println(newCours.getId());
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
                    cour.setMoniteur(newCours.getMoniteur());
                    return repository.save(cour);
                })
                .orElseGet(() -> {
                    newCours.setId(id);
                    return repository.save(newCours);
                });
    }

    @DeleteMapping("/cour/{id}")
    public void deleteCours(@PathVariable Long id, HttpServletResponse response) throws IOException {
        repository.deleteById(id);
        JSONObject jo = new JSONObject();
        jo.put("type", "deleteCours");
        jo.put("status", response.getStatus());
        response.getWriter().println(jo.toString());
    }
}