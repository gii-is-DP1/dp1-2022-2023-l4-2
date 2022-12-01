package org.springframework.samples.petclinic.partida;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface ParticipacionRepository extends CrudRepository<Participacion,Integer>{
    
    List<Participacion> findAll();

}
