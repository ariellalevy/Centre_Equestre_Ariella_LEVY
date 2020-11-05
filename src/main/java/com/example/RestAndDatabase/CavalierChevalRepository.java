package com.example.RestAndDatabase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CavalierChevalRepository extends CrudRepository<CavalierCheval, Long> {

    List<CavalierCheval> findByCavalier(String cavalier);

    CavalierCheval findById(long id);

    List<CavalierCheval> findByIdCour(int id);

    @Query("SELECT id FROM CavalierCheval WHERE (idCour = :idCour and cavalier = :cavalier)")
    Long getId(@Param("idCour") int idCour, @Param("cavalier") String cavalier);
}
