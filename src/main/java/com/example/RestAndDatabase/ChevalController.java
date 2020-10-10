package com.example.RestAndDatabase;

import org.springframework.web.bind.annotation.*;

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
    public Cheval newCheval(@RequestBody Cheval newCheval) {
        return repository.save(newCheval);
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
    public void deleteCheval(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
