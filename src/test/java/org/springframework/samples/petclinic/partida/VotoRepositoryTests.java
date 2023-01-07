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
import org.springframework.samples.petclinic.jugador.RolType;
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
    public void findAllTest() throws VotoDuplicadoException{
        //Añadir voto
        Voto voto = new Voto();
        voto.setRonda(2);
        voto.setTurno(1);
        votoService.saveVoto(voto,null);

        //Test
        List<Voto> votos = votoRepository.findAll();
        assertNotNull(votos);
        assertFalse(votos.isEmpty());
    }

    @Test
    public void findVotosRondaTurnoTest() throws VotoDuplicadoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();

        //Voto
        Voto voto = new Voto();
        voto.setRonda(1);
        voto.setTurno(1);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        //Test
        List<Voto> votos = votoRepository.findVotosRondaTurno(partida1.getRonda(), partida1.getTurno(), partida1);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void findVotosRondaTurnoFailTest() throws VotoDuplicadoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();

        //Voto
        Voto voto = new Voto();
        voto.setRonda(4); //Ronda que no existe
        voto.setTurno(1);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        //Test
        List<Voto> votos = votoRepository.findVotosRondaTurno(partida1.getRonda(), partida1.getTurno(), partida1);
        assertTrue(votos.isEmpty());
    }

    @Test
    public void findVotosTurnoJugadorTest() throws VotoDuplicadoException{
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

        //Test
        List<Voto> votos = votoRepository.findVotosTurnoJugador(jugador, partida1.getRonda(), partida1.getTurno(), partida1);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void findVotosTurnoJugadorFailTest() throws VotoDuplicadoException{
        //Partida
        Partida partida1 = partidaService.getPartidaById(1).get();
        Jugador jugador = jugadorService.getJugadorByUsername("Campanas");
        Jugador jugador2 = jugadorService.getJugadorByUsername("Guaje");
        Voto voto = new Voto();

        //Añado voto sin ningun jugador
        voto.setRonda(1);
        voto.setTurno(1);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        //Test buscando un jugador que no está
        List<Voto> votos = votoRepository.findVotosTurnoJugador(jugador, partida1.getRonda(), partida1.getTurno(), partida1);
        assertTrue(votos.isEmpty());

        //Voto con ronda que no existe
        voto.setRonda(2);
        voto.setTurno(1);
        voto.setJugador(jugador2);
        voto.setPartida(partida1);
        votoService.saveVoto(voto,null);

        //Test buscando una ronda que no esta en la partida
        List<Voto> votos2 = votoRepository.findVotosTurnoJugador(jugador2, partida1.getRonda(), partida1.getTurno(), partida1);
        assertTrue(votos2.isEmpty());
    }

}
