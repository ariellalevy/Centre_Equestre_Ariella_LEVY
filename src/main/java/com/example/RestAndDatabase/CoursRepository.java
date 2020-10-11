package com.example.RestAndDatabase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursRepository extends CrudRepository<Cours, Long> {

    List<Cours> findByTitre(String titre);

    List<Cours> findByNiveau(int niveau);

    Cours findById(long id);
}
