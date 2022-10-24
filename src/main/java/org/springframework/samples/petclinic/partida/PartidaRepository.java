package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository {
    List<Partida> findAll();
}
