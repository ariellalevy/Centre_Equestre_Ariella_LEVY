package com.example.RestAndDatabase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursRepository extends CrudRepository<Cours, Long> {

    List<Cours> findByMoniteur(String moniteur);

    List<Cours> findByNiveau(int niveau);

    Cours findById(long id);
}
