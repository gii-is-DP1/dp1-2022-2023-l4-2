package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.jugador.Jugador;
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
    public void jugadoresConOpcionesDePartidaTest(){
        Partida partida = new Partida();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Participacion participacion1 = new Participacion();
        Participacion participacion2 = new Participacion();
        participacion1.setEsAnfitrion(true);
        participacion1.setId(99);
        participacion1.setNumConsul(1);
        //participacion1.setPartidas(partida);
        participacion2.setEsAnfitrion(false);
        participacion2.setId(123);
        participacion2.setNumConsul(3);
        //participacion2.setPartidas(partida);
        jugador1.setId(10);
        jugador1.setFirstName("Paco");
        jugador1.setLastName("Candela");
        jugador1.setPartidas(List.of(partida));
        jugador1.setParticipaciones(List.of(participacion1));
        jugador2.setId(11);
        jugador2.setFirstName("Paquito");
        jugador2.setLastName("Navarro");
        jugador2.setPartidas(List.of(partida));
        jugador2.setParticipaciones(List.of(participacion2));
        partida.setId(99);
        partida.setJugadores(List.of(jugador1, jugador2));
        Map<Jugador,List<FaccionType>> res = partidaService.jugadoresConOpcionesDePartida(partida);
        assertEquals(2, res.entrySet().stream().map(x->x.getValue()).collect(Collectors.toList()).get(0).size());
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

    @Test
    public void getPartidasPageablesSuccessTest(){
        Integer numPartidas = 5;
        Pageable pag = PageRequest.of(0,numPartidas);
        List<Partida> partidaspaginadas = partidaService.getPartidasPageables(pag);
        assertEquals(partidaspaginadas.size(), numPartidas);

    }

    
    @Test
    public void getPartidasPageablesFailTest(){
        Integer numPartidas = 5000;
        Pageable pag = PageRequest.of(0,numPartidas);
        List<Partida> partidaspaginadas = partidaService.getPartidasPageables(pag);
        assertNotEquals(partidaspaginadas.size(), numPartidas);

    }

    @Test
    public void getFaccionesTypeTest(){
        List<FaccionType> facciones = partidaService.getFaccionesType();
        assertNotNull(facciones);
        assertFalse(facciones.isEmpty());
    }

    @Test
    public void getFaccionesTypeByNameTest(){
        FaccionType faccion = partidaService.getFaccionesTypeByName("Leal");
        assertNotNull(faccion);
        assertEquals("Leal", faccion.toString());
    }

    @Test
    public void getFaccionesTypeByNameFailTest(){
        FaccionType faccion = partidaService.getFaccionesTypeByName("Leal");
        assertNotNull(faccion);
        assertNotEquals("Traidor", faccion.toString());
    }

}
