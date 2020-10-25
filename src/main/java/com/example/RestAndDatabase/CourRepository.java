package com.example.RestAndDatabase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourRepository extends CrudRepository<Cour, Long> {

    Optional<Cour> findByMoniteur(String moniteur);

    List<Cour> findByCavalier(String cavalier);

    Cour findById(long id);
}
