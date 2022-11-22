package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<Voto,Integer>{
    List<Voto> findAll();

    @Query("SELECT v FROM Voto v WHERE v.ronda = :ronda AND v.turno = :turno")
    List<Voto> findVotosRondaTurno(@Param("ronda") Long ronda, @Param("turno") Long turno);
}
