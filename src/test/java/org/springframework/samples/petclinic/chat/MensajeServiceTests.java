package org.springframework.samples.petclinic.chat;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MensajeServiceTests {

    
    @Autowired
    protected MensajeService mensajeService;
    @Autowired
    protected JugadorService jugadorService;

    @Test
    void shouldGetMensajes(){
        List<Mensaje> mensajes = mensajeService.getAll();

        assertNotNull(mensajes);
        assertFalse(mensajes.isEmpty());
    }

    @Test
    void shouldFindByJugadorId(){
        List<Mensaje> mensajes = mensajeService.getAll().stream().filter(x -> x.getJugador().getId() == 3).collect(Collectors.toList());
        List<Mensaje> find = mensajeService.getAllByJugadorId(jugadorService.getJugadorById(3).get());
        assertEquals(mensajes, find);
    }

    @Test
    void negativeFindByJugadorId(){
        assertThrows(NoSuchElementException.class, () -> mensajeService.getAllByJugadorId(jugadorService.getJugadorById(-1).get())); 
    }

    @Test
    void shouldSaveMensaje(){
        Mensaje m = new Mensaje();
        m.setContenido("Hola Test");
        m.setJugador(jugadorService.getJugadorByUsername("Antaca"));
        mensajeService.saveMensaje(m);
        Mensaje saved = mensajeService.getAll().stream().filter(x -> x.getId() == 30).findAny().get();
        assertEquals(saved.getId(), (Integer) 30);
    }

    @Test
    void negativeSaveChat(){
        Mensaje m = null;
        assertThrows(InvalidDataAccessApiUsageException.class, () -> mensajeService.saveMensaje(m)); 
    }
    
}
