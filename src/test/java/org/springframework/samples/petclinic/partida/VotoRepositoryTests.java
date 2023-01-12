package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class VotoRepositoryTests {

    protected VotoRepository votoRepository;
    protected VotoService votoService;
    protected PartidaService partidaService;
    protected JugadorService jugadorService;

    @Autowired
    public VotoRepositoryTests(VotoRepository votoRepository, VotoService votoService, PartidaService partidaService, JugadorService jugadorService){
        this.votoRepository = votoRepository;
        this.partidaService = partidaService;
        this.votoService = votoService;
        this.jugadorService = jugadorService;
    }
    

    @Test
    public void findAllTest() throws VotoNoPermitidoException{
        List<Voto> votos = votoRepository.findAll();
        assertNotNull(votos);
        assertFalse(votos.isEmpty());
    }

    @Test
    public void findVotosRondaTurnoTest() throws VotoNoPermitidoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        partida1.setRonda(1);
        partida1.setTurno(1);
        //Test
        List<Voto> votos = votoRepository.findVotosRondaTurno(partida1.getRonda(), partida1.getTurno(), partida1);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void findVotosRondaTurnoFailTest() throws VotoNoPermitidoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        partida1.setRonda(1);
        partida1.setTurno(2);
        //Test
        List<Voto> votos = votoRepository.findVotosRondaTurno(partida1.getRonda(), partida1.getTurno(), partida1);
        assertTrue(votos.isEmpty());
    }

    @Test
    public void findVotosTurnoJugadorTest() throws VotoNoPermitidoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        Jugador jugador = jugadorService.getJugadorByUsername("Guaje");
        partida1.setRonda(1);
        partida1.setTurno(1);
        //Test
        List<Voto> votos = votoRepository.findVotosTurnoJugador(jugador, partida1.getRonda(), partida1.getTurno(), partida1);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void findVotosTurnoJugadorFailTest() throws VotoNoPermitidoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        Jugador jugador = jugadorService.getJugadorByUsername("Campanas");
        Jugador jugador2 = jugadorService.getJugadorByUsername("Guaje");


        //Test buscando un jugador que no está
        List<Voto> votos = votoRepository.findVotosTurnoJugador(jugador, partida1.getRonda(), partida1.getTurno(), partida1);
        assertTrue(votos.isEmpty());

        //Test buscando una ronda que no esta en la partida
        partida1.setRonda(3);
        List<Voto> votos2 = votoRepository.findVotosTurnoJugador(jugador2, partida1.getRonda(), partida1.getTurno(), partida1);
        assertTrue(votos2.isEmpty());
    }

}
