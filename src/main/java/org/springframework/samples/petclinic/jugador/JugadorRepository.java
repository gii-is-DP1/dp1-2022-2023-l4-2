package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Long> {

    List<Jugador> findAll();

    
}
