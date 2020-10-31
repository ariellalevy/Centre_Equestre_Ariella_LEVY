package com.example.RestAndDatabase;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
public class ChevalController {

    private ChevalRepository repository;
    Long id = Long.valueOf(1);

    public ChevalController(ChevalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/chevaux")
    public Iterable<Cheval> chevaux(){
        return repository.findAll();
    }

    @GetMapping("/cheval/{id}")
    public Optional<Cheval> cheval(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/cheval")
    public void newCheval(@RequestBody Cheval newCheval, HttpServletResponse response) throws IOException {
        repository.save(newCheval);
        response.getWriter().println(newCheval.getId());
    }

    @PutMapping("/cheval/{id}")
    public Cheval replaceCheval(@RequestBody Cheval newCheval, @PathVariable Long id) {

        return repository.findById(id)
                .map(cheval -> {
                    cheval.setNom(newCheval.getNom());
                    cheval.setType(newCheval.getType());
                    cheval.setPoids(newCheval.getPoids());
                    cheval.setTaille(newCheval.getTaille());
                    return repository.save(cheval);
                })
                .orElseGet(() -> {
                    newCheval.setId(id);
                    return repository.save(newCheval);
                });
    }

    @DeleteMapping("/cheval/{id}")
    public void deleteCheval(@PathVariable Long id, HttpServletResponse response) throws IOException {
        repository.deleteById(id);
        JSONObject jo = new JSONObject();
        jo.put("type", "deleteCheval");
        jo.put("status", response.getStatus());
        response.getWriter().println(jo.toString());
    }
}
