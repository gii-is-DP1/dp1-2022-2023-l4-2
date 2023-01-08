package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
import org.springframework.samples.petclinic.user.User;
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
        participacion2.setEsAnfitrion(false);
        participacion2.setId(123);
        participacion2.setNumConsul(3);
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
        List<Partida> partidas1 = partidaService.getPartidas();
        partidaService.deletePartida(4);
        List<Partida> partidas2 = partidaService.getPartidas();
        assertNotEquals(partidas1.size(), partidas2.size());
        Partida p = partidaService.getPartidaById(4).orElse(null);
        assertNull(p);
        
    }

    @Test
    public void deletePartidaFalloTest(){
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
    public void getPartidasNoActivasTest(){
        List<Partida> partidasNoActivas = partidaService.getPartidasNoActivas();
        assertNotNull(partidasNoActivas);
        assertFalse(partidasNoActivas.size()==0);
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


    @Test
    public void cambiarVotoSuccessTest(){
        Voto v1 = new Voto();
        FaccionType f = new FaccionType();
        f.setName("Traidor");
        v1.setFaccion(f);
        Voto v2 = partidaService.cambiarVoto(v1);
        assertNotNull(v2);
        assertEquals("Leal", v2.getFaccion().toString());
    }

    @Test
    public void cambiarVotoSuccessTest2(){
        Voto v1 = new Voto();
        FaccionType f = new FaccionType();
        f.setName("Leal");
        v1.setFaccion(f);
        Voto v2 = partidaService.cambiarVoto(v1);
        assertNotNull(v2);
        assertEquals("Traidor", v2.getFaccion().toString());
    }

    // Si por un casual se enviase un voto del tipo mercader se cambiaría a leal
    @Test
    public void cambiarVotoFailTest(){
        Voto v1 = new Voto();
        FaccionType f = new FaccionType();
        f.setName("Mercader");
        v1.setFaccion(f);
        Voto v2 = partidaService.cambiarVoto(v1);
        assertNotNull(v2);
        assertEquals("Leal", v2.getFaccion().toString());
    }
    
    @Test
    public void añadeJugadorAPartidaTest(){
        Partida p = partidaService.getPartidaById(1).get();
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(j1);
        jugadores.add(j2);
        p.setJugadores(jugadores);
        Integer numeroJugadores = p.getJugadores().size();
        Jugador j3 = new Jugador();
        partidaService.añadeJugadorAPartida(j3, p);
        assertEquals(numeroJugadores+1, p.getJugadores().size());
    }

    @Test
    public void añadeJugadorAPartidaTestFailed(){
        Partida p = partidaService.getPartidaById(1).get();
        Jugador j1 = new Jugador();
        Jugador j2 = new Jugador();
        List<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(j1);
        jugadores.add(j2);
        p.setJugadores(jugadores);
        Integer numeroJugadores = p.getJugadores().size();
        //J1 ya estaba en la partida, si lo vuelvo a intentar añadir no deberia añadirlo
        partidaService.añadeJugadorAPartida(j1, p);
        assertEquals(numeroJugadores, p.getJugadores().size());
    }

    @Test
    public void crearPartidaTest(){
        Jugador j1 = new Jugador();
        Partida p = new Partida();
        p.setNumJugadores(6);
        User u = new User();
        u.setUsername("Pablo");
        j1.setUser(u);
        j1.setFirstName("Pablo");
        partidaService.CrearPartida(j1, p);
        assertNotNull(p.getJugadores());
        assertEquals("Pablo", p.getAnfitrion());
    }

    @Test
    public void comprobarSiSobrepasaLimiteTest(){
        Partida p = new Partida();
        p.setNumJugadores(6);
        p.setRonda(2);
        p.setTurno(4);
        p.setLimite(15);
        p.setVotosContraCesar(16);
        p.setVotosFavorCesar(2);
        partidaService.comprobarSiSobrepasaLimite(p);
        assertNotNull(p.getLimite());
        assertEquals(3, p.getRonda());
    }

    @Test
    public void comprobarSiSobrepasaLimiteTest2(){
        Partida p = new Partida();
        p.setNumJugadores(6);
        p.setRonda(2);
        p.setTurno(4);
        p.setLimite(15);
        p.setVotosContraCesar(14);
        p.setVotosFavorCesar(2);
        partidaService.comprobarSiSobrepasaLimite(p);
        assertNotNull(p.getLimite());
        assertEquals(2, p.getRonda());
    }

    
}

