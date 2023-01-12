package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer> {

    List<Jugador> findAll();

    @Query("SELECT j FROM Jugador j")
    List<Jugador> findAllPageable(Pageable pageable);

    @Query("SELECT j FROM Jugador j WHERE j.user.username =?1")
    Jugador findJugadorByUsername(String username); 
    
    @Query("SELECT DISTINCT j FROM Jugador j WHERE j.user.username LIKE :username%")
    Collection<Jugador> findJugadoresByUsername(@Param("username") String username);

    @Query("SELECT r FROM RolType r")
    List<RolType> findRoles();


}
