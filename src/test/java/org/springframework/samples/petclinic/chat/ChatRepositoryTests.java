package org.springframework.samples.petclinic.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.estadistica.Logro;
import org.springframework.samples.petclinic.estadistica.LogroRepository;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorRepository;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ChatRepositoryTests {

    @Autowired
    protected ChatRepository chatRepository;

    @Test
    void shouldFindAll(){
        List<Chat> list = chatRepository.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldFindByPartidaId(){
        Chat c = chatRepository.findByPartidaId(8L);
        List<Chat> chats = chatRepository.findAll();
        Chat c2 = chats.stream().filter(x -> x.getId()==8L).findFirst().get();
        assertEquals(c, c2);
    }
}
