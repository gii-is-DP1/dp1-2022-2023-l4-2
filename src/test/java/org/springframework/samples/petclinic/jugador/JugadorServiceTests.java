package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {
    
    @Autowired
    protected JugadorService jugadorService;

    @Test
    public void getJugadoresTest(){
        List<Jugador> jugadores = jugadorService.getJugadores();
        assertFalse(jugadores.isEmpty());
    }


    @Test
    public void getJugadorByIdTest(){
        assertEquals("Pablo", jugadorService.getJugadorById(6).get().getFirstName());
    }

    @Test
    public void gestJugadorByIdFailTest(){
        assertNotEquals("Pedro", jugadorService.getJugadorById(6).get().getFirstName());
    }

    @Test
    public void deleteJugadorTest(){
        Jugador j = new Jugador();
        User u = new User();
        u.setUsername("javi24");
        j.setUser(u);
        j.setFirstName("Javier");
        j.setLastName("Varo");
        jugadorService.saveJugador(j);

        Integer id = jugadorService.getJugadorByUsername(u.getUsername()).getId();
        try{
            jugadorService.deleteJugador(id);
            Jugador j2 = jugadorService.getJugadorById(id).orElse(null);
            assertNull(j2);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }

    @Test
    public void deleteJugadorFailTest(){
        assertThrows(EmptyResultDataAccessException.class,()->jugadorService.deleteJugador(345344));
    }


    @Test
    public void editJugadorTest(){
        Jugador jugador = jugadorService.getJugadorById(6).get();
        jugador.setEstaEnPartida(true);
        jugadorService.editJugador(jugador);
        assertEquals(true, jugadorService.getJugadorById(6).get().isEstaEnPartida());
    }

    @Test
    public void editJugadorFailTest(){
        Jugador j = new Jugador();
        User u = new User();
        u.setUsername("javi24");
        j.setUser(u);
        j.setFirstName("Javier");
        j.setLastName("Varo");
        j.setId(49959596);
        assertThrows(NoSuchElementException.class,()->jugadorService.editJugador(j));
    }

    @Test
    public void saveJugadorTest(){
        Jugador j = new Jugador();
        User u = new User();
        u.setUsername("javielillo");
        u.setPassword("1324134");
        j.setUser(u);
        j.setFirstName("Javier");
        j.setLastName("Varo");
        try{
            jugadorService.saveJugador(j);
            Jugador j2 = jugadorService.getJugadorByUsername(u.getUsername());
            assertEquals(j, j2);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }

    @Test
    public void saveJugadorFailTest(){
        Jugador j = new Jugador();
        j.setFirstName("Javier");
        j.setLastName("Varo");
        assertThrows(NullPointerException.class,()->jugadorService.saveJugador(j));
    }

    @Test
    public void agregarAmigoTest(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());
        assertTrue(jugador1.getAmigoDe().contains(jugador6));
    }

    @Test
    public void agregarAmigoFailTest(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());

        Integer amigos = jugador1.getAmigoDe().size();
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());
        assertEquals(amigos, jugador1.getAmigoDe().size());
    }


    @Test
    public void deleteAmigoTest(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());
        try{
            jugadorService.deleteAmigo(jugador1.getUser().getUsername(), jugador6.getUser().getUsername());
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }

    @Test
    public void deleteAmigoFailTest(){
        Jugador jugador6 = jugadorService.getJugadorById(6).get();
        Jugador jugador1 = jugadorService.getJugadorById(1).get();
        assertThrows(Exception.class,()->jugadorService.deleteAmigo(jugador1.getUser().getUsername(), jugador6.getUser().getUsername()));
    
        
    }

    @Test
    public void getJugadorByUsernameTest(){
        Jugador jugador = jugadorService.getJugadorById(6).get();
        assertEquals(jugador, jugadorService.getJugadorByUsername("Colombiano"));
    }

    @Test
    public void getJugadorByUsernameTestFail(){
        Jugador j = jugadorService.getJugadorByUsername("Manolito");
        assertNull(j);
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
        List<Partida> listaVacia = jugadorService.getPartidasActivasAmigos(jugador1);
        jugadorService.agregarAmigo(jugador6, jugador1.getUser().getUsername());
        assertEquals(listaVacia, jugadorService.getPartidasActivasAmigos(jugador6));
    }
}