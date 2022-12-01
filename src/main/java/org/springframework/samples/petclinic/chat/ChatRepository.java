package org.springframework.samples.petclinic.chat;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {

    List<Chat> findAll();

    @Query("SELECT c FROM Chat c WHERE c.partida.id = :id")
    Chat findByPartidaId(@Param("id") Long id);
    
}
