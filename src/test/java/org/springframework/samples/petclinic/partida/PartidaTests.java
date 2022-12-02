package org.springframework.samples.petclinic.partida;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Partida.class))
public class PartidaTests {

    
    @Test
    public void comprobarFaccionGanadora1(){
        Partida p = new Partida();
        p.setRonda(3);
        p.setVotosContraCesar(2);
        p.setVotosFavorCesar(5);
        p.setNumJugadores(5L);
        p.setLimite(p.calculaLimite(5L));
        p.setFaccionGanadora(p.calculoFaccionGanadora());

        assertEquals("Leal",p.calculoFaccionGanadora().getName());
    }

    @Test
    public void comprobarFaccionGanadora2(){
        Partida p = new Partida();
        p.setRonda(3);
        p.setVotosContraCesar(5);
        p.setVotosFavorCesar(2);
        p.setNumJugadores(5L);
        p.setLimite(p.calculaLimite(5L));
        p.setFaccionGanadora(p.calculoFaccionGanadora());

        assertEquals("Traidor",p.calculoFaccionGanadora().getName());
    }

    @Test
    public void comprobarFaccionGanadora3(){
        Partida p = new Partida();
        p.setRonda(3);
        p.setVotosContraCesar(4);
        p.setVotosFavorCesar(5);
        p.setNumJugadores(5L);
        p.setLimite(p.calculaLimite(5L));
        p.setFaccionGanadora(p.calculoFaccionGanadora());


        assertEquals("Mercader",p.calculoFaccionGanadora().getName());
    }
    

    @Test
    public void comprobarFaccionGanadora4(){
        Partida p = new Partida();
        Participacion p2 = new Participacion();
        FaccionType f = new FaccionType();
        List<Participacion> aux = new ArrayList<>();
        f.setId(2);
        f.setName("Traidor");
        p2.setFaccionApoyada(f);
        aux.add(p2);
        p.setRonda(3);
        p.setVotosContraCesar(2);
        p.setVotosFavorCesar(18);
        p.setNumJugadores(5);
        p.setLimite(p.calculaLimite(5L));
        p.setParticipaciones(aux);
        p.setFaccionGanadora(p.calculoFaccionGanadora());

        assertEquals("Traidor",p.calculoFaccionGanadora().getName());
    }

    @Test
    public void comprobarFaccionGanadora5(){
        Partida p = new Partida();
        Participacion p2 = new Participacion();
        FaccionType f = new FaccionType();
        List<Participacion> aux = new ArrayList<>();
        f.setId(1);
        f.setName("Leal");
        p2.setFaccionApoyada(f);
        aux.add(p2);
        p.setRonda(3);
        p.setVotosContraCesar(18);
        p.setVotosFavorCesar(5);
        p.setNumJugadores(5);
        p.setLimite(p.calculaLimite(5L));
        p.setParticipaciones(aux);
        p.setFaccionGanadora(p.calculoFaccionGanadora());

        assertEquals("Leal",p.calculoFaccionGanadora().getName());
    }

    @Test
    public void comprobarFaccionGanadora6(){
        Partida p = new Partida();
        p.setRonda(3);
        p.setVotosContraCesar(18);
        p.setVotosFavorCesar(1);
        p.setNumJugadores(5);
        p.setLimite(p.calculaLimite(5L));
        p.setParticipaciones(List.of());
        p.setFaccionGanadora(p.calculoFaccionGanadora());


        assertEquals("Mercader",p.calculoFaccionGanadora().getName());
    }

    @Test
    public void comprobarLimites1(){
        Partida p = new Partida();
        p.setLimite(p.calculaLimite(5L));
        assertEquals(13L, p.getLimite());
    }

    @Test
    public void comprobarLimites2(){
        Partida p = new Partida();
        p.setLimite(p.calculaLimite(6L));
        assertEquals(15L, p.getLimite());
    }

    @Test
    public void comprobarLimites3(){
        Partida p = new Partida();
        p.setLimite(p.calculaLimite(7L));
        assertEquals(17L, p.getLimite());
    }

    @Test
    public void comprobarLimites4(){
        Partida p = new Partida();
        p.setLimite(p.calculaLimite(8L));
        assertEquals(20L, p.getLimite());
    }

    @Test
    public void comprobarTiempoFinalTest(){
        Partida p = new Partida();
        p.setFechaInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 8, 30)));
        LocalDateTime fechaFinal = LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 58, 20));
        p.setTiempo(p.calculaTiempoFinal(p.getFechaInicio(), fechaFinal));
        assertFalse(p.getTiempo() < 0);
        assertEquals(50, p.getTiempo());

    }

    @Test
    public void comprobarTiempoFinalTest2(){
        Partida p = new Partida();
        p.setFechaInicio(LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 55, 30)));
        LocalDateTime fechaFinal = LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 18, 20));
        p.setTiempo(p.calculaTiempoFinal(p.getFechaInicio(), fechaFinal));
        assertFalse(p.getTiempo() < 0);
        assertEquals(23, p.getTiempo());

    }
}
