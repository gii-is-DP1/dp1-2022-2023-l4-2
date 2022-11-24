package org.springframework.samples.petclinic.mensaje;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository  extends CrudRepository<Mensaje, Integer>{

    List<Mensaje> findAll();

    @Query("SELECT m FROM Mensaje m WHERE m.jugador.id = :id")
    List<Mensaje> findByJugadorId(@Param("id") Integer id);
    
}