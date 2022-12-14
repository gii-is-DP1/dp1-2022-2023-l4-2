package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VotoServiceTests {
    
    protected VotoService votoService;
    protected PartidaService partidaService;
    protected JugadorService jugadorService;

    @Autowired
    public VotoServiceTests(VotoService votoService, PartidaService partidaService, JugadorService jugadorService){
        this.partidaService = partidaService;
        this.votoService = votoService;
        this.jugadorService = jugadorService;
    }

   
    @Test
    public void getVotosTest() throws VotoNoPermitidoException{
        //Añadir voto
        Voto voto = new Voto();
        voto.setRonda(2);
        voto.setTurno(1);
        votoService.saveVoto(voto,null);

        List<Voto> votos = votoService.getVotos();
        assertNotNull(votos);
        assertFalse(votos.isEmpty());
    }

    @Test
    public void getVotosRondaTurnoTest() throws VotoNoPermitidoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();

        //Voto
        Voto voto = new Voto();
        voto.setRonda(1);
        voto.setTurno(1);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        List<Voto> votos = votoService.getVotosRondaTurno(partida1);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void getVotosRondaTurnoFailTest() throws VotoNoPermitidoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();

        //Voto
        Voto voto = new Voto();
        voto.setRonda(4); //Ronda que no existe
        voto.setTurno(1);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        List<Voto> votos = votoService.getVotosRondaTurno(partida1);
        assertTrue(votos.isEmpty());
    }

    @Test
    public void saveVotoTest() throws VotoNoPermitidoException {
        Voto voto = new Voto();
        voto.setRonda(2);
        voto.setTurno(1);
        votoService.saveVoto(voto,null);

        List<Voto> votos = votoService.getVotos();
        assertEquals(voto, votos.get(votos.size()-1));
    }
    
    @Test
    public void getVotosTurnoJugadorTest() throws Exception{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        Jugador jugador = jugadorService.getJugadorByUsername("Guaje");

        //Voto
        Voto voto = new Voto();
        voto.setRonda(1);
        voto.setTurno(1);
        voto.setJugador(jugador);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        List<Voto> votos = votoService.getVotosTurnoJugador(partida1, jugador);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void getVotosTurnoJugadorFailTest() throws Exception{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        Jugador jugador = jugadorService.getJugadorByUsername("JugadorPrueba");
        Jugador jugador2 = jugadorService.getJugadorByUsername("Manolo");
        Voto voto = new Voto();

        //Voto con jugador que no esta en la partida
        voto.setRonda(1);
        voto.setTurno(1);
        voto.setJugador(jugador); //Jugador que no esta en la partida
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        assertThrows(Exception.class,()->votoService.getVotosTurnoJugador(partida1, jugador));

        //Voto con ronda que no existe
        voto.setRonda(2);
        voto.setTurno(1);
        voto.setJugador(jugador2);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        List<Voto> votos = votoService.getVotosTurnoJugador(partida1, jugador2);
        assertTrue(votos.isEmpty());
    }

    @Test
    public void getVotoByIdTest() throws VotoNoPermitidoException {
        //Añadir voto
        Voto voto = new Voto();
        voto.setRonda(2);
        voto.setTurno(1);
        votoService.saveVoto(voto,null);

        Voto voto2 = votoService.getVotoById((long) voto.getId()).orElse(null);
        
        assertNotNull(voto2); 
        assertEquals(voto, voto2);
    }

    @Test
    public void getVotoByIdFailTest() {
        Voto voto = votoService.getVotoById((long) 5467834).orElse(null);
        assertNull(voto);
    }
}
