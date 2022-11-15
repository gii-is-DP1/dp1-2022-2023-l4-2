package org.springframework.samples.petclinic.partidas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaServiceTests {
    
    @Autowired
    protected PartidaService partidaService;

    @Test
    public void getPartidasTest(){
        List<Partida> partidas = partidaService.getPartidas();
        assertNotNull(partidas);
        assertFalse(partidas.isEmpty());
    }

    @Test
    public void getPartidasByIdTest(){
        Partida partida1 = partidaService.getPartidaById(1).get();
        assertNotNull(partida1);
        assertEquals("Guaje", partida1.getAnfitrion());
    }

    @Test
    public void getPartidasByIdFalloTest(){
        Partida partida1 = partidaService.getPartidaById(1).get();
        assertNotNull(partida1);
        assertNotEquals("Antaca", partida1.getAnfitrion());
    }

    @Test
    public void deletePartidaTest(){
        List<Partida> partidas1 = partidaService.getPartidas();
        List<Partida> partidas2 = partidaService.getPartidas();
        partidaService.deletePartida(partidas1.get(0).getId());
        assertNotEquals("Guaje", partidas1.get(0).getAnfitrion());
        assertNotEquals(partidas1.get(0), partidas2.get(0));


    }

    @Test
    public void deletePartidaFalloTest(){
        List<Partida> partidas1 = partidaService.getPartidas();
        List<Partida> partidas2 = partidaService.getPartidas();
        partidaService.deletePartida(partidas1.get(0).getId());
        assertEquals("Jose", partidas1.get(0).getAnfitrion());
        assertNotEquals(partidas1.get(0), partidas2.get(0));

    }
}
