package org.springframework.samples.petclinic.partidas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.samples.petclinic.partida.FaccionType;
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
        //List<Partida> partidas1 = partidaService.getPartidas();
        //L7ist<Partida> partidas2 = partidaService.getPartidas();
        //partidaService.deletePartida(partidas1.get(0).getId());
        //assertNotEquals("Guaje", partidas1.get(0).getAnfitrion());
        //assertNotEquals(partidas1.get(0).getId(), partidas2.get(0).getId());
        List<Partida> partidas1 = partidaService.getPartidas();
        partidaService.deletePartida(4);
        List<Partida> partidas2 = partidaService.getPartidas();
        assertNotEquals(partidas1.size(), partidas2.size());
        Partida p = partidaService.getPartidaById(4).orElse(null);
        assertNull(p);
        
    }

    @Test
    public void deletePartidaFalloTest(){
        //List<Partida> partidas1 = partidaService.getPartidas();
        //List<Partida> partidas2 = partidaService.getPartidas();
        //partidaService.deletePartida(partidas1.get(0).getId());
        //assertEquals("Jose", partidas1.get(0).getAnfitrion());
        //assertNotEquals(partidas1.get(0), partidas2.get(0));
        
        assertThrows(EmptyResultDataAccessException.class,()->partidaService.deletePartida(345344));

        
    }

    @Test
    public void savePartidaSuccessTest(){
        Partida p = new Partida();
        p.setAnfitrion("Guaje");
        p.setRonda(1);
        p.setTurno(1);
        p.setNumJugadores(8);
        p.setVotosContraCesar(0);
        p.setVotosFavorCesar(0);
        p.setLimite(15);
        FaccionType f = new FaccionType();
        f.setName("Inventada");
        p.setFaccionGanadora(f);

        partidaService.save(p);

        Partida p2 = partidaService.getPartidaById(p.getId()).get();

        assertNotNull(p2);
        assertEquals(p2, p);

        
    }

    @Test
    public void editPartidaFailTest(){
        Partida p = new Partida();
        p.setAnfitrion("Guaje");
        p.setRonda(1);
        p.setTurno(1);
        p.setNumJugadores(8);
        p.setVotosContraCesar(0);
        p.setVotosFavorCesar(0);
        p.setLimite(15);
        FaccionType f = new FaccionType();
        f.setName("Inventada");
        p.setFaccionGanadora(f);
        //No puedo editar una partida que no existe
        assertThrows(NoSuchElementException.class,()->partidaService.edit(p));
    }

    @Test
    public void editPartidaSuccessTest(){
        Partida p = partidaService.getPartidaById(1).get();
        String anfitrion = p.getAnfitrion();
        p.setAnfitrion("Joselillo");
        partidaService.edit(p);
        
        Partida p2 = partidaService.getPartidaById(1).get();
        
        assertNotEquals(anfitrion, p2.getAnfitrion());

    }

    @Test
    public void getPartidasActivasTest(){
        List<Partida> partidasActivas = partidaService.getPartidasActivas();
        assertNotNull(partidasActivas);
        assertFalse(partidasActivas.size()==0);

    }

}
