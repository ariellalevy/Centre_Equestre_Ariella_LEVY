package com.example.RestAndDatabase;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class CourController {

    private CourRepository repository;
    Long id = Long.valueOf(1);

    public CourController(CourRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/courAll")
    public Iterable<Cour> cour(){
        return repository.findAll();
    }

    @GetMapping("/courMoniteur/{moniteur}")
    public Optional<Cour> courMoniteur(@PathVariable String moniteur){
        return repository.findByMoniteur(moniteur);
    }

    @GetMapping ("/courCavalier")
    public List<Cour> cour(@RequestParam(value="cavalier") String cavalier) {
        return repository.findByCavalier(cavalier);
    }

    @PostMapping("/courDetail")
    public void newCour(@RequestBody Cour newCour, HttpServletResponse response) throws IOException {
        repository.save(newCour);
        response.getWriter().println(newCour.getId());
    }

    @PutMapping("/courModify/{id}")
    public Cour replaceCour(@RequestBody Cour newCour, @PathVariable Long id) {

        return repository.findById(id)
                .map(cour -> {
                    cour.setIdCour(newCour.getIdCour());
                    cour.setMoniteur(newCour.getMoniteur());
                    cour.setCavalier(newCour.getCavalier());
                    cour.setCheval(newCour.getCheval());
                    return repository.save(cour);
                })
                .orElseGet(() -> {
                    newCour.setId(id);
                    return repository.save(newCour);
                });
    }

    @DeleteMapping("/courDelete/{id}")
    public void deleteCour(@PathVariable Long id, HttpServletResponse response) throws IOException {
        repository.deleteById(id);
        JSONObject jo = new JSONObject();
        jo.put("type", "deleteCours");
        jo.put("status", response.getStatus());
        response.getWriter().println(jo.toString());
    }
}