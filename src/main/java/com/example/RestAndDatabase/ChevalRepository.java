package com.example.RestAndDatabase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChevalRepository extends CrudRepository<Cheval, Long>  {

    List<Cheval> findByNom(String nom);

    Cheval findById(long id);
}
