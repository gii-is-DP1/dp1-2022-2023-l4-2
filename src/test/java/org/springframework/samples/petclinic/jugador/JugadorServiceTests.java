package org.springframework.samples.petclinic.jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.samples.petclinic.partida.Participacion;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class JugadorServiceTests {

    Jugador j1 = new Jugador();
    Jugador j2 = new Jugador();
    Jugador j3 = new Jugador();
    Jugador j4 = new Jugador();
    Jugador j5 = new Jugador();
    Partida p = new Partida();

    @BeforeEach
    public void config(){
        j1.setFirstName("FEKIR");
        j1.setLastName("Fekir");
        j1.setId(123123);
        User u = new User();
        u.setEnabled(true);
        u.setPassword("Fekircillo");
        u.setPassword("adfsadf");
        j1.setUser(u);
        User u2 = new User();
        u2.setEnabled(true);
        u2.setPassword("Fekircillo2");
        u2.setPassword("adfsadf2");
        j2.setId(12341);
        j2.setUser(u2);
        j2.setFirstName("Luiz");
        j2.setLastName("Padrique");
        User u3 = new User();
        u3.setEnabled(true);
        u3.setPassword("Fekircillo3");
        u3.setPassword("adfsadf2");
        j3.setId(5787554);
        j3.setUser(u3);
        j3.setFirstName("Luiz");
        j3.setLastName("Padrique");
        User u4 = new User();
        u4.setEnabled(true);
        u4.setPassword("Fekircillo4");
        u4.setPassword("adfsadf2");
        j4.setId(534);
        j4.setUser(u4);
        j4.setFirstName("Luiz");
        j4.setLastName("Padrique");
        User u5 = new User();
        u5.setEnabled(true);
        u5.setPassword("Fekircillo5");
        u5.setPassword("adfsadf2");
        j5.setId(5664);
        j5.setUser(u5);
        j5.setFirstName("Luiz");
        j5.setLastName("Padrique");
        p.setTurno(1);
        Participacion part1 = new Participacion();
        part1.setNumConsul(1);
        part1.setId(134);
        Participacion part2 = new Participacion();
        part2.setNumConsul(2);
        part2.setId(3467);
        Participacion part3 = new Participacion();
        part3.setNumConsul(3);
        part3.setId(6756);
        Participacion part4 = new Participacion();
        part4.setNumConsul(4);
        part4.setId(980);
        Participacion part5 = new Participacion();
        part5.setNumConsul(5);
        part5.setId(938);
        p.setNumJugadores(5);
        j1.setPartidas(List.of(p));
        j2.setPartidas(List.of(p));
        j1.setParticipaciones(List.of(part1));
        j2.setParticipaciones(List.of(part2));
        j3.setParticipaciones(List.of(part3));
        j4.setParticipaciones(List.of(part4));
        j5.setParticipaciones(List.of(part5));
        p.setParticipaciones(List.of(part1,part2,part3,part4,part5));
        p.setJugadores(List.of(j1,j2,j3,j4,j5));
        assertEquals(j1.getParticipacionEnPartida(p), part1);
        assertEquals(j2.getParticipacionEnPartida(p), part2);
        assertEquals(j3.getParticipacionEnPartida(p), part3);
        assertEquals(j4.getParticipacionEnPartida(p), part4);
        assertEquals(j5.getParticipacionEnPartida(p), part5);
    }
    
    @Autowired
    protected JugadorService jugadorService;

    @Autowired
    protected PartidaService partidaService;

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
    public void getJugadorByIdFailTest(){
        assertThrows(NoSuchElementException.class, () -> jugadorService.getJugadorById(77).get().getFirstName());
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
        Jugador jugador7 = jugadorService.getJugadorById(7).get();
        assertThrows(Exception.class,()->jugadorService.deleteAmigo(jugador7.getUser().getUsername(), jugador6.getUser().getUsername()));
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
        Jugador jugador7 = jugadorService.getJugadorById(7).get();
        Jugador jugador8 = jugadorService.getJugadorById(8).get();
        List<Partida> listaVacia = jugadorService.getPartidasActivasAmigos(jugador8);
        jugadorService.agregarAmigo(jugador8, jugador7.getUser().getUsername());
        assertEquals(listaVacia, jugadorService.getPartidasActivasAmigos(jugador8));
    }

    @Test
    public void testGetRoles(){
        List<RolType> roles = jugadorService.getRoles();
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        assertTrue(!roles.stream()
                .filter(x->x.getName().equals("Consul"))
                .collect(Collectors.toList())
                .isEmpty());
        assertTrue(!roles.stream()
                .filter(x->x.getName().equals("Edil"))
                .collect(Collectors.toList())
                .isEmpty());
        assertTrue(!roles.stream()
                .filter(x->x.getName().equals("Pretor"))
                .collect(Collectors.toList())
                .isEmpty());
        assertTrue(!roles.stream()
                .filter(x->x.getName().equals("Sin rol"))
                .collect(Collectors.toList())
                .isEmpty());
    }

    @Test
    public void testReparteRolesRonda2(){
        jugadorService.preparaRolesRonda2(p);
        assertEquals("Consul",j1.getRol().getName());
        assertEquals("Sin rol", j2.getRol().getName());
    }

    @Test
    public void testAñadePartidaAJugador(){
        Jugador j1 = jugadorService.getJugadorByUsername("Guaje");
        Partida p1 = partidaService.getPartidaById(5).orElse(null);
        assertFalse(j1.getPartidas().contains(p1));
        jugadorService.añadePartidaAJugador(p1, j1);
        assertTrue(j1.getPartidas().contains(p1));
    }

    @Test
    public void testAñadePartidaAJugadorNegative(){ //Probamos a añadirle una partida en la que ya haya participado y comprobamos que no ocurre nada extraño
        Jugador j1 = jugadorService.getJugadorByUsername("Guaje");
        Partida p1 = partidaService.getPartidaById(1).orElse(null);
        assertTrue(j1.getPartidas().contains(p1));
        jugadorService.añadePartidaAJugador(p1, j1);
        assertTrue(j1.getPartidas().contains(p1));
        assertEquals(j1.getPartidas().stream().filter(x->x.equals(p1)).count(), 1.);// Compruebo que la partida solo se ha añadido una vez
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void reparteRolesTest(int i){
        p.setTurno(i);
        jugadorService.reparteRoles(p);
        if(p.getTurno() ==1){
            assertEquals(j1.getRol().getName(), "Consul");
            assertEquals(j2.getRol().getName(), "Pretor");
            assertEquals(j3.getRol().getName(), "Edil");
            assertEquals(j4.getRol().getName(), "Edil");
            assertEquals(j5.getRol().getName(), "Sin rol");
        }
        if(p.getTurno() ==2){
            assertEquals(j2.getRol().getName(), "Consul");
            assertEquals(j3.getRol().getName(), "Pretor");
            assertEquals(j4.getRol().getName(), "Edil");
            assertEquals(j5.getRol().getName(), "Edil");
            assertEquals(j1.getRol().getName(), "Sin rol");
        }
        if(p.getTurno() ==3){
            assertEquals(j3.getRol().getName(), "Consul");
            assertEquals(j4.getRol().getName(), "Pretor");
            assertEquals(j5.getRol().getName(), "Edil");
            assertEquals(j1.getRol().getName(), "Edil");
            assertEquals(j2.getRol().getName(), "Sin rol");
        }
        if(p.getTurno() ==4){
            assertEquals(j4.getRol().getName(), "Consul");
            assertEquals(j5.getRol().getName(), "Pretor");
            assertEquals(j1.getRol().getName(), "Edil");
            assertEquals(j2.getRol().getName(), "Edil");
            assertEquals(j3.getRol().getName(), "Sin rol");
        }
        if(p.getTurno() ==5){
            assertEquals(j5.getRol().getName(), "Consul");
            assertEquals(j1.getRol().getName(), "Pretor");
            assertEquals(j2.getRol().getName(), "Edil");
            assertEquals(j3.getRol().getName(), "Edil");
            assertEquals(j4.getRol().getName(), "Sin rol");
        }
    }

    @Test
    public void reestableceRolesDeNoElegidosTest(){
        j1.setYaElegido(true);
        j2.setYaElegido(false);
        j3.setYaElegido(true);
        j4.setYaElegido(false);
        j5.setYaElegido(true);
        RolType rt = new RolType();
        rt.setName("Sin rol");
        RolType rt2 = new RolType();
        rt2.setName("Consul");
        jugadorService.reestableceRolesDeNoElegidos(p, rt2, rt);
        assertEquals(j2.getRol().getName(), "Sin rol");
        assertEquals(j4.getRol().getName(), "Sin rol");
    }

    @Test
    public void jugadoresQuePuedenSerEdilTest(){
        RolType rt2 = new RolType();
        rt2.setName("Consul");
        j1.setRol(rt2);
        j2.setYaElegido(true);
        j1.setYaElegido(false);
        j3.setYaElegido(false);
        j4.setYaElegido(false);
        j5.setYaElegido(false);
        RolType rt = new RolType();
        rt.setId(23453);
        rt.setName("Sin nombre");
        j3.setRol(rt);
        j4.setRol(rt);
        j5.setRol(rt);
        List<Jugador> ls = jugadorService.jugadoresQuePuedenSerEdil(p);
        assertEquals(3, ls.size());
        assertTrue(ls.containsAll(List.of(j3,j4,j5)));

    }

    @Test
    public void jugadoresQuePuedenSerEdilTestCon8Jugadores(){
        p.setNumJugadores(8);
        RolType rt2 = new RolType();
        RolType rt3 = new RolType();
        rt3.setName("Edil");
        rt2.setName("Consul");
        j1.setRol(rt2);
        j2.setYaElegido(true);
        j1.setYaElegido(false);
        j3.setYaElegido(false);
        j4.setYaElegido(false);
        j5.setYaElegido(false);
        RolType rt = new RolType();
        rt.setId(23453);
        rt.setName("Sin nombre");
        j3.setRol(rt3);
        j4.setRol(rt3);
        j5.setRol(rt);
        List<Jugador> ls = jugadorService.jugadoresQuePuedenSerEdil(p);
        assertEquals(1, ls.size());
        assertTrue(ls.containsAll(List.of(j5)));

    }

    @Test
    public void consulRonda2Test(){
        RolType rt2 = new RolType();
        RolType rt3 = new RolType();
        rt3.setName("Edil");
        rt2.setName("Consul");
        j1.setRol(rt3);
        j2.setRol(rt2);
        j3.setRol(rt3);
        j4.setRol(rt3);
        j5.setRol(rt3);
        p.setRonda(2);
        p.setTurno(3);
        jugadorService.consulRonda2(p, jugadorService.getRoles());
        assertEquals(j3.getRol().getName(), "Consul");
        assertEquals(p.getJugadores().stream().filter(x->x.getRol().getName().equals("Sin rol")).count(), 1);
    }

    @Test
    public void cambiaRondaTest(){
        p.setRonda(1);
        p.setTurno(p.getNumJugadores()+1);
        jugadorService.cambiaRonda(p);
        assertEquals(p.getRonda(), 2);
        assertEquals(p.getTurno(), 1);
    }

    

}