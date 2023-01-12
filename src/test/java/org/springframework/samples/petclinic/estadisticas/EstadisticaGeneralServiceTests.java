package org.springframework.samples.petclinic.estadisticas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.estadistica.EstadisticaGeneralService;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EstadisticaGeneralServiceTests {
    
    @Autowired
    protected EstadisticaGeneralService estadisticaGeneralService;

    @Test
    public void getPartidasNoActivasTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertFalse(partidas.isEmpty());
    }

    @Test
    public void getJugadoresTest(){
        List<Jugador> jugadores = estadisticaGeneralService.getJugadores();
        assertNotNull(jugadores);
        assertFalse(jugadores.isEmpty());
    }

    @Test
    public void getTopJugadoresConVictoriasTest(){
        List<Jugador> jugadores = estadisticaGeneralService.getJugadores();
        jugadores.forEach(x->x.getPartidasGanadas());
        List<Jugador> topJugadoresVictorias = estadisticaGeneralService.getTopJugadoresConVictorias(jugadores);
        assertNotNull(topJugadoresVictorias);
        assertEquals(jugadores.get(1), topJugadoresVictorias.get(0));
    }

    @Test
    public void getTopJugadoresConVictoriasFailTest(){
        List<Jugador> jugadores = estadisticaGeneralService.getJugadores();
        jugadores.forEach(x->x.getPartidasGanadas());
        List<Jugador> topJugadoresVictorias = estadisticaGeneralService.getTopJugadoresConVictorias(jugadores);
        assertNotNull(topJugadoresVictorias);
        assertNotEquals(jugadores.get(4), topJugadoresVictorias.get(0));
    }

    @Test
    public void getTopJugadoresConPartidasTest(){
        List<Jugador> jugadores = estadisticaGeneralService.getJugadores();
        jugadores.forEach(x->x.getPartidasJugadas());
        List<Jugador> topJugadoresPartidas = estadisticaGeneralService.getTopJugadoresConPartidas(jugadores);
        assertNotNull(topJugadoresPartidas);
        assertEquals(jugadores.get(1), topJugadoresPartidas.get(0));
    }

    @Test
    public void getTopJugadoresConPartidasFailTest(){
        List<Jugador> jugadores = estadisticaGeneralService.getJugadores();
        jugadores.forEach(x->x.getPartidasJugadas());
        List<Jugador> topJugadoresPartidas = estadisticaGeneralService.getTopJugadoresConPartidas(jugadores);
        assertNotNull(topJugadoresPartidas);
        assertNotEquals(jugadores.get(2), topJugadoresPartidas.get(0));
    }

    @Test
    public void getMaxVotosEnContraCesarTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(14, estadisticaGeneralService.getMaxVotosEnContraCesar(partidas));
    }

    @Test
    public void getMaxVotosEnContraCesarFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(13, estadisticaGeneralService.getMaxVotosEnContraCesar(partidas));
    }

    @Test
    public void getMaxVotosAFavorCesarTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(19, estadisticaGeneralService.getMaxVotosAFavorCesar(partidas));
    }

    @Test
    public void getMaxVotosAFavorCesarFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(13, estadisticaGeneralService.getMaxVotosAFavorCesar(partidas));
    }

    @Test
    public void getMaxDifVotosTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(7, estadisticaGeneralService.getMaxDifVotos(partidas));
    }

    @Test
    public void getMaxDifVotosFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(16, estadisticaGeneralService.getMaxVotosAFavorCesar(partidas));
    }

    @Test
    public void getNumPartidasTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(6, estadisticaGeneralService.getNumPartidas(partidas));
    }

    @Test
    public void getNumPartidasFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(10, estadisticaGeneralService.getNumPartidas(partidas));
    }

    @Test
    public void getMediaMinutosPartidaTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(26f, estadisticaGeneralService.getMediaMinutosPartida(partidas));
    }

    @Test
    public void getMediaMinutosPartidaFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(20f, estadisticaGeneralService.getMediaMinutosPartida(partidas));
    }

    @Test
    public void getFaccionPerdedoraTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals("Leal", estadisticaGeneralService.getFaccionPerdedora(partidas));
    }

    @Test
    public void getFaccionPerdedoraFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals("Leal", estadisticaGeneralService.getFaccionPerdedora(partidas));
    }

    @Test
    public void getVictoriasLealTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(3, estadisticaGeneralService.getVictoriasLeal(partidas));
    }

    @Test
    public void getVictoriasLealFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(5, estadisticaGeneralService.getVictoriasLeal(partidas));
    }

    @Test
    public void getVictoriasTraidorTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(2, estadisticaGeneralService.getVictoriasTraidor(partidas));
    }

    @Test
    public void getVictoriasTraidorFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(5, estadisticaGeneralService.getVictoriasTraidor(partidas));
    }

    @Test
    public void getVictoriasMercaderTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertEquals(1, estadisticaGeneralService.getVictoriasMercader(partidas));
    }

    @Test
    public void getVictoriasMercaderFailTest(){
        List<Partida> partidas = estadisticaGeneralService.getPartidasNoActivas();
        assertNotNull(partidas);
        assertNotEquals(5, estadisticaGeneralService.getVictoriasMercader(partidas));
    }
}
