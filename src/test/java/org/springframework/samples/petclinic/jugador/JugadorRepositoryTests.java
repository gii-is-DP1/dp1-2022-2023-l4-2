package org.springframework.samples.petclinic.jugador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.estadistica.Logro;
import org.springframework.samples.petclinic.estadistica.LogroRepository;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorRepositoryTests {

    @Autowired
    protected JugadorRepository jugadorRepository;

    @Test
    public void findAllTest(){
        List<Jugador> list = jugadorRepository.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void findJugadorByUsernameTest(){
        Jugador jugador = jugadorRepository.findById(6).get();
        assertEquals(jugador, jugadorRepository.findJugadorByUsername("Colombiano"));
    }

    @Test
    public void findJugadorByUsernameFailTest(){
        Jugador j = jugadorRepository.findJugadorByUsername("Manolito");
        assertNull(j);
    }

    @Test
    public void findJugadoresByUsernameTest(){  
        Collection<Jugador> jugadores = new ArrayList<Jugador>();
        Jugador jugador = jugadorRepository.findById(6).get();
        jugadores.add(jugador);
        assertEquals(jugadores, jugadorRepository.findJugadoresByUsername("Colombian"));
    }

    @Test
    public void findJugadoresByUsernameFailTest(){
        Collection<Jugador> jugadores = jugadorRepository.findJugadoresByUsername("Manolito");
        assertTrue(jugadores.isEmpty());  
    }

    @Test
    public void findRolesTest(){
        List<RolType> roles = jugadorRepository.findRoles();
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
    }   
}
