package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Long>{
    List<Partida> findAll();

    @Query("SELECT p FROM Partida p")
    List<Partida> findAllPageable(Pageable pageable);

    @Query("SELECT p FROM Partida p WHERE p.activa =TRUE")
    List<Partida> findPartidasActivas();

    @Query("SELECT p FROM Partida p WHERE p.activa =FALSE")
    List<Partida> findPartidasNoActivas();

    @Query("SELECT f FROM FaccionType f")
    List<FaccionType> findAllFaccionType();

    @Query("SELECT f FROM FaccionType f WHERE f.name = :name")
    FaccionType findFaccionTypeByName(@Param("name") String name);
}
