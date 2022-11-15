package org.springframework.samples.petclinic.jugador;

import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {
    
    @Autowired
    protected JugadorService jugadorService;

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
        Jugador j = new Jugador();
        User u = new User();
        u.setUsername("javi");
        j.setUser(u);
        j.setFirstName("Javier");
        j.setLastName("Varo");      
        j.setId(7);
        j.setEstaEnPartida(true);
        j.setAmigoDe(null);
        jugadorService.saveJugador(j);
        try{
            jugadorService.deleteJugador(7);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }


    @Test
    public void TestEditJugador(){
        Jugador jugador = jugadorService.getJugadorById(6).get();
        jugador.setEstaEnPartida(true);
        jugadorService.editJugador(jugador);
        assertEquals(true, jugadorService.getJugadorById(6).get().isEstaEnPartida());
    }

    @Test
    public void TestSaveJugador(){
        Jugador j = new Jugador();
        User u = new User();
        u.setUsername("javi");
        j.setUser(u);
        j.setFirstName("Javier");
        j.setLastName("Varo");
        try{
            jugadorService.saveJugador(j);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }

    @Test
    public void TestAgregarAmigo(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());
        assertTrue(jugador1.getAmigoDe().contains(jugador6));
    }


    @Test
    public void TestDeleteAmigo(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        try{
            jugadorService.deleteAmigo(jugador6.getUser().getUsername(), jugador1.getUser().getUsername());;
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }


    /*
     * @Test
    public void TestDeleteAmigoFail(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        assertThrows(NotFoundException.class,
        ()->jugadorService.deleteAmigo(jugador6.getUser().getUsername(), jugador1.getUser().getUsername()));
    }
     */

    @Test
    public void TestGetJugadorByUsername(){
        Jugador jugador = jugadorService.getJugadorById(6).get();
        assertEquals(jugador, jugadorService.getJugadorByUsername("Colombiano"));
    }


    @Test
    public void TestGetJugadorByUsernameFail(){
        Jugador jugador = jugadorService.getJugadorById(1).get();
        assertNotEquals(jugador, jugadorService.getJugadorByUsername("Colombiano"));
    }


    @Test
    public void TestGetPartidasActivasAmigos(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador3 = jugadorService.getJugadorById(3).get();
        jugadorService.agregarAmigo(jugador6, jugador3.getUser().getUsername());
        assertNotNull(jugadorService.getPartidasActivasAmigos(jugador6));
    }


    @Test
    public void TestGetPartidasActivasAmigosFail(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        List<Partida> listaVacia = new ArrayList<>();
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());
        assertEquals(listaVacia, jugadorService.getPartidasActivasAmigos(jugador6));
    }
}