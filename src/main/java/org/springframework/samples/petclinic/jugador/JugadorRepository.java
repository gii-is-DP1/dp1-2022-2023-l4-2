package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer> {

    List<Jugador> findAll();

    @Query("SELECT j FROM Jugador j WHERE j.user.username =?1")
    Jugador findJugadorByUsername(String username);  
}
