package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.jugador.RolType;
import org.springframework.samples.petclinic.user.User;
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

    Jugador j= new Jugador();
    Partida p = new Partida();
    Voto v= new Voto();

    @BeforeEach
    public void config(){
        p.setId(123141);
        User u = new User();
        u.setUsername("Pepito");
        u.setPassword("gawdf");
        j.setUser(u);
        j.setFirstName("wrert");
        j.setLastName("wrtewre");
        RolType rt = new RolType();
        rt.setId(123123);
        rt.setName("Edil");
        j.setId(13413);
        j.setRol(rt);
        v.setId(21512);
        v.setElegido(false);
        FaccionType ft = new FaccionType();
        ft.setId(31413);
        ft.setName("Leal");
        v.setFaccion(ft);
        v.setJugador(j);
        v.setRonda(1);
        v.setTurno(1);
        v.setPartida(p);
        p.setJugadores(List.of(j));
    }

   
    @Test
    public void getVotosTest() throws VotoNoPermitidoException{
        List<Voto> votos = votoService.getVotos();
        assertNotNull(votos);
        assertFalse(votos.isEmpty());
    }

    @Test
    public void getVotosRondaTurnoTest() throws Exception{
        Partida partida = partidaService.getPartidaById(1).orElse(null);
        partida.setRonda(1);
        partida.setTurno(1);
        List<Voto> votos = votoService.getVotosRondaTurno(partida);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void getVotosRondaTurnoFailTest() throws VotoNoPermitidoException{
        Partida partida = partidaService.getPartidaById(1).orElse(null);
        partida.setRonda(1);
        partida.setTurno(2);
        List<Voto> votos = votoService.getVotosRondaTurno(partida);
        assertTrue(votos.isEmpty());
    }
    
    @Test
    public void getVotosTurnoJugadorTest() throws Exception{
        Partida partida = partidaService.getPartidaById(1).orElse(null);
        partida.setRonda(1);
        partida.setTurno(1);
        Jugador jugador = jugadorService.getJugadorById(1).orElse(null);
        List<Voto> votos = votoService.getVotosTurnoJugador(partida, jugador);
        assertFalse(votos.isEmpty());
        assertNotNull(votos);
    }

    @Test
    public void getVotosTurnoJugadorFailTest() throws Exception{
        //Partida
        Partida partida = partidaService.getPartidaById(1).orElse(null);
        partida.setRonda(1);
        partida.setTurno(1);
        Jugador jugador = jugadorService.getJugadorById(1).orElse(null);
        Jugador jugador2 = jugadorService.getJugadorById(6).orElse(null);

        assertThrows(Exception.class,()->votoService.getVotosTurnoJugador(partida, jugador2));
        //Voto con ronda que no existe
        partida.setRonda(2);
        partida.setTurno(2);
        List<Voto> votos = votoService.getVotosTurnoJugador(partida, jugador);
        assertTrue(votos.isEmpty());
    }

    @Test
    public void getVotoByIdTest() throws VotoNoPermitidoException {
        Voto voto2 = votoService.getVotoById(1L).orElse(null);
        assertNotNull(voto2); 

    }

    @Test
    public void getVotoByIdFailTest() {
        Voto voto = votoService.getVotoById((long) 5467834).orElse(null);
        assertNull(voto);
    }

    @Test
    public void saveVotoDeConsul(){
        RolType rt = new RolType();
        rt.setId(12341234);
        rt.setName("Consul");
        j.setRol(null);
        assertThrows(VotoNoPermitidoException.class, ()->votoService.saveVoto(v, j));
    }

    @Test
    public void saveVotoDeEdilRepetido(){
        RolType rt = new RolType();
        rt.setId(123123);
        rt.setName("Edil");
        j.setRol(rt);
        try{
            votoService.saveVoto(v, j);
        }catch(Exception E){

        }
        v.setId(2324);
        assertThrows(VotoNoPermitidoException.class, ()->votoService.saveVoto(v, j));
    }


}
