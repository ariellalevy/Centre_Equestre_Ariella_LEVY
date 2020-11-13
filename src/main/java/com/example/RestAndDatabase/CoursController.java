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
    private CavalierChevalRepository repositoryCavalierCheval;
    Long id = Long.valueOf(1);
    public CoursController(CoursRepository repository, CavalierChevalRepository repositoryCavalierCheval) {
        this.repository = repository;
        this.repositoryCavalierCheval = repositoryCavalierCheval;
    }

    @GetMapping("/cours")
    public Iterable<Cours> cours(){
        Iterable<Cours> listCour = repository.findAll();
        for (Cours cours : listCour){
            if (Utility.calculeDate(cours.getDateCours())==true){
                cours.setIs24before(true);
            }
        }
        return listCour;
    }

    @GetMapping("/cour/{id}")
    public Optional<Cours> cour(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping ("/cour")
    public List<Cours> cour(@RequestParam(value="niveau") int niveau) {
        List<Cours> listCour = repository.findByNiveau(niveau);
        for (Cours cours : listCour){
            if (Utility.calculeDate(cours.getDateCours())==true){
                cours.setIs24before(true);
            }
        }
        return listCour;
    }

    @GetMapping ("/coursMoniteur")
    public List<Cours> courListMoniteur(@RequestParam(value="moniteur") String moniteur) {
        List<Cours> listCour = repository.findByMoniteur(moniteur);
        for (Cours cours : listCour){
            if (Utility.calculeDate(cours.getDateCours())==true){
                cours.setIs24before(true);
            }
        }
        return listCour;
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
                    if(newCours.getTitre()!=null){
                        cour.setTitre(newCours.getTitre());
                    }if(newCours.getDateCours()!=null){
                        cour.setDateCours(newCours.getDateCours());
                    }if(newCours.getHoraire()!=null){
                        cour.setHoraire(newCours.getHoraire());
                    }if(newCours.getNbrCavalier()!=0){
                        cour.setNbrCavalier(newCours.getNbrCavalier());
                    }if(newCours.getNiveau()!=0){
                        cour.setNiveau(newCours.getNiveau());
                    }if(newCours.getMoniteur()!=null){
                        cour.setMoniteur(newCours.getMoniteur());
                    }
                    return repository.save(cour);
                })
                .orElseGet(() -> {
                    newCours.setId(id);
                    return repository.save(newCours);
                });
    }

    @DeleteMapping("/cour/{id}")
    public void deleteCours(@PathVariable Long id, HttpServletResponse response) throws IOException {
        List<CavalierCheval> cavalierChevalList = repositoryCavalierCheval.findByIdCour(Math.toIntExact(id));
        for(CavalierCheval cavalierCheval : cavalierChevalList){
            repositoryCavalierCheval.delete(cavalierCheval);
        }
        repository.deleteById(id);
        JSONObject jo = new JSONObject();
        jo.put("type", "deleteCours");
        jo.put("status", response.getStatus());
        response.getWriter().println(jo.toString());
    }
}