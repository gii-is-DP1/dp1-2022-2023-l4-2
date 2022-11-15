package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.partida.Participacion;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {
    
    @Autowired
    protected JugadorService jugadorService;

    /*Authorities aut1;
    Partida p;

    public void config(){
        aut1.setAuthority("owner");
        p = new Partida();
        Set<Authorities> aut = Set.of(aut1);
        List<Partida> partidas = List.of(p);
        List<Participacion> participaciones = null;
        User u = new User();
        u.setUsername("Guaje");
        u.setAuthorities(aut);
        u.setEnabled(true);
        u.setPassword("1111");
        Jugador j = new Jugador();        
        j.setUser(u);
        j.setPartidas(partidas);
        j.setParticipaciones(participaciones);
        j.setEstaEnPartida(true);
        jugadorService.saveJugador(j);
    }*/

    @Test
    public void TestListingJugadores(){
        List<Jugador> jugadores = jugadorService.getJugadores();
        assertNotNull(jugadores);
        assertFalse(jugadorService.getJugadores().isEmpty());
    }

    @Test
    public void TestGetJugadorById(){
        List<Jugador> jugadores = jugadorService.getJugadores();
        assertNotNull(jugadores);
        assertEquals("Pablo", jugadorService.getJugadorById(6).get().getFirstName());
    }

    @Test
    public void TestGetJugadorByIdFallo(){
        List<Jugador> jugadores = jugadorService.getJugadores();
        assertNotNull(jugadores);
        assertNotEquals("Campos", jugadorService.getJugadorById(6).get().getLastName());
    }

    @Test
    public void TestDeleteJugador(){
        try{
            jugadorService.deleteJugador(6);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }

    /*@Test
    public void TestDeleteJugadorFail(){
        try{
            jugadorService.deleteJugador(33);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }*/
}
