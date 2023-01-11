package org.springframework.samples.petclinic.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MensajeRepositoryTests {

    @Autowired
    protected MensajeRepository mensajeRepository;

    @Test
    void shouldFindAll(){
        List<Mensaje> list = mensajeRepository.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    void shouldFindByJugadorId(){
        List<Mensaje> m = mensajeRepository.findByJugadorId(3);
        List<Mensaje> mensajes = mensajeRepository.findAll();
        List<Mensaje> m2 = mensajes.stream().filter(x -> x.getJugador().getId() == 3).collect(Collectors.toList());
        assertEquals(m, m2);
    }
    
}
