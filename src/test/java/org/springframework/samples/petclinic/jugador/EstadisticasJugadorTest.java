package org.springframework.samples.petclinic.jugador;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.samples.petclinic.partida.FaccionType;
import org.springframework.samples.petclinic.partida.Participacion;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.User;

public class EstadisticasJugadorTest {

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();
    Jugador jugador3 = new Jugador();
    Jugador jugador4 = new Jugador();
    Jugador jugador5 = new Jugador();
    Partida p1 = new Partida();
    Partida p2 = new Partida();
    Partida p3 = new Partida();
    Partida p4 = new Partida();
    Participacion part1 = new Participacion();
    Participacion part2 = new Participacion();
    Participacion part3 = new Participacion();
    Participacion part4 = new Participacion();
    
    @BeforeEach
    public void config(){
        User u1 = new User();
        u1.setUsername("PEPILLO");
        u1.setPassword("213132");
        jugador1.setAmigoDe(List.of());
        jugador1.setParticipaciones(List.of());
        jugador1.setPartidas(List.of());

        User u2 = new User();
        u2.setUsername("PEPILLO2");
        u2.setPassword("213132");
        jugador2.setAmigoDe(List.of());
        jugador2.setParticipaciones(List.of());
        jugador2.setPartidas(List.of());

        User u3 = new User();
        u3.setUsername("PEPILLO3");
        u3.setPassword("213132");
        jugador3.setAmigoDe(List.of());
        jugador3.setParticipaciones(List.of());
        jugador3.setPartidas(List.of());

        User u4 = new User();
        u4.setUsername("PEPILLO4");
        u4.setPassword("213132");
        jugador4.setAmigoDe(List.of());
        jugador4.setParticipaciones(List.of());
        jugador4.setPartidas(List.of());

        User u5 = new User();
        u5.setUsername("PEPILLO5");
        u5.setPassword("213132");
        jugador5.setAmigoDe(List.of());
        jugador5.setParticipaciones(List.of());
        jugador5.setPartidas(List.of());

        FaccionType ft1 = new FaccionType();
        ft1.setName("Leal");
        FaccionType ft2 = new FaccionType();
        ft2.setName("Traidor");
        FaccionType ft3 = new FaccionType();
        ft3.setName("Mercader");
        
        part1.setEsAnfitrion(true);
        part1.setFaccionApoyada(ft1);
        part1.setNumConsul(1);
        part1.setOpciones(List.of(ft1,ft2));
        part1.setVotosContraCesar(2);
        part1.setVotosFavorCesar(3);
        part1.setVotosNeutros(1);

        part2.setEsAnfitrion(true);
        part2.setFaccionApoyada(ft2);
        part2.setNumConsul(2);
        part2.setOpciones(List.of(ft2,ft1));
        part2.setVotosContraCesar(4);
        part2.setVotosNeutros(2);
        part2.setVotosFavorCesar(0);

        part3.setEsAnfitrion(true);
        part3.setFaccionApoyada(ft3);
        part3.setNumConsul(2);
        part3.setOpciones(List.of(ft2,ft1));
        part3.setVotosContraCesar(4);
        part3.setVotosNeutros(2);
        part3.setVotosFavorCesar(0);

        part4.setEsAnfitrion(true);
        part4.setFaccionApoyada(ft2);
        part4.setNumConsul(2);
        part4.setOpciones(List.of(ft2,ft1));
        part4.setVotosContraCesar(4);
        part4.setVotosNeutros(2);
        part4.setVotosFavorCesar(0);

        p1.setId(2352345);
        p1.setActiva(false);
        p1.setAnfitrion("PEPILLO");
        p1.setFaccionGanadora(ft1);
        p1.setTiempo(20);
        p1.setJugadores(List.of(jugador1));
        p1.setParticipaciones(List.of(part1));

        p2.setActiva(false);
        p2.setAnfitrion("PEPILLO");
        p2.setFaccionGanadora(ft1);
        p2.setTiempo(25);
        p2.setJugadores(List.of(jugador1));
        p2.setParticipaciones(List.of(part2));

        p3.setActiva(false);
        p3.setAnfitrion("PEPILLO");
        p3.setFaccionGanadora(ft3);
        p3.setTiempo(25);
        p3.setJugadores(List.of(jugador1));
        p3.setParticipaciones(List.of(part3));

        p4.setActiva(false);
        p4.setAnfitrion("PEPILLO");
        p4.setFaccionGanadora(ft2);
        p4.setTiempo(25);
        p4.setJugadores(List.of(jugador1));
        p4.setParticipaciones(List.of(part4));

        jugador1.setPartidas(List.of(p1,p2,p3,p4));
        jugador1.setParticipaciones(List.of(part1,part2,part3,part4));

        jugador1.setAmigoDe(List.of(jugador2,jugador3,jugador4));
        jugador2.setAmigoDe(List.of(jugador1));
        jugador3.setAmigoDe(List.of(jugador1));
        jugador5.setAmigoDe(List.of(jugador1));
    }

    @Test
    public void testNumeroPartidasJugadas(){
        config();
        assertEquals(4, jugador1.getPartidasJugadas());
    }

    @Test 
    public void testNumeroPartidasGanadas(){
        config();
        assertEquals(3, jugador1.getPartidasGanadas());
    }

    @Test
    public void testGetVictoriasComoLeal(){
        config();
        assertEquals(1,jugador1.getVictoriasComoLeal());
    }

    @Test
    public void testGetVictoriasComoTraidor(){
        config();
        assertEquals(1, jugador1.getVictoriasComoTraidor());
    }

    @Test
    public void testGetVictoriasComoMercader(){
        config();
        assertEquals(1, jugador1.getVictoriasComoMercader());
    }

    @Test
    public void testTiempoJugado(){
        config();
        assertEquals(95, jugador1.getTiempoJugado());
    }

    @Test
    public void testFaccionFavorita(){
        config();
        assertTrue(jugador1.getFaccionFavorita().equals("Traidor"));
    }

    @Test
    public void testGetParticipacionEnPartida(){
        config();
        assertEquals(jugador1.getParticipacionEnPartida(p1), part1);
        assertEquals(jugador1.getParticipacionEnPartida(p2), part2);
        assertEquals(jugador1.getParticipacionEnPartida(p3), part3);
        assertEquals(jugador1.getParticipacionEnPartida(p4), part4);
    }

    @Test
    public void testNumeroSeguidos(){
        config();
        Integer res = jugador1.getnNumeroSeguidos();
        assertEquals(3, res);
    }

    @Test
    public void testNumeroSeguidores(){
        config();
        Integer res=jugador1.getNumeroDeSeguidores(List.of(jugador2,jugador3,jugador4,jugador5));
        assertEquals(res, 3);
    }

    @Test
    public void testNumeroAmigos(){
        config();
        Integer res = jugador1.getNumeroAmigos();
        assertEquals(res, 2);
    }
}
