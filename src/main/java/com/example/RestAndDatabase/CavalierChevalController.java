package com.example.RestAndDatabase;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CavalierChevalController {

    private CavalierChevalRepository repository;
    private CoursRepository coursRepository;
    Long id = Long.valueOf(1);

    public CavalierChevalController(CavalierChevalRepository repository, CoursRepository coursRepository) {
        this.repository = repository;
        this.coursRepository = coursRepository;
    }

    @GetMapping("/listCavalierCheval")
    public Iterable<CavalierCheval> listCavalierCheval(){
        return repository.findAll();
    }

    @GetMapping ("/idByCavalier")
    public List<CavalierCheval> idByCavalier(@RequestParam(value="cavalier") String cavalier) {
        return repository.findByCavalier(cavalier);
    }

    @GetMapping ("/getByIdCour")
    public Long getByIdCour(@RequestParam(value="idCour") int idCour,@RequestParam(value="cavalier") String cavalier) {
        return repository.getId(idCour,cavalier);
    }

    @PostMapping("/addCavalierCheval")
    public void addCavalierCheval(@RequestBody CavalierCheval cavalierCheval, HttpServletResponse response) throws IOException {
        repository.save(cavalierCheval);
        response.getWriter().println(cavalierCheval.getId());
    }

    @PutMapping("/addCheval/{id}")
    public CavalierCheval replaceAddCheval(@RequestBody CavalierCheval cavalierCheval, @PathVariable Long id) {

        return repository.findById(id)
                .map(cheval -> {
                    cheval.setCheval(cavalierCheval.getCheval());
                    return repository.save(cheval);
                })
                .orElseGet(() -> {
                    cavalierCheval.setId(id);
                    return repository.save(cavalierCheval);
                });
    }

    @DeleteMapping("/deleteCavalierCheval/{id}")
    public void deleteCavalierCheval(@PathVariable Long id, HttpServletResponse response) throws IOException {
        repository.deleteById(id);
        JSONObject jo = new JSONObject();
        jo.put("type", "deleteCavalierCheval");
        jo.put("status", response.getStatus());
        response.getWriter().println(jo.toString());
    }
}