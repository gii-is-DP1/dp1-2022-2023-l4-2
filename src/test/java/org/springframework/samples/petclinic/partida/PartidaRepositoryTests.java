package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaRepositoryTests {
    
    @Autowired
    protected PartidaRepository partidaRepository;

    @Test
    public void findAllTest(){
        List<Partida> partidas = partidaRepository.findAll();
        assertNotNull(partidas);
        assertFalse(partidas.isEmpty());
    }

    @Test
    public void findAllPageableTest(){
        Integer numPartidas = 5;
        Pageable pag = PageRequest.of(0,numPartidas);
        List<Partida> partidaspaginadas = partidaRepository.findAllPageable(pag);
        assertEquals(partidaspaginadas.size(), numPartidas);
    }

    @Test
    public void findAllPageableFailTest(){
        Integer numPartidas = 5000;
        Pageable pag = PageRequest.of(0,numPartidas);
        List<Partida> partidaspaginadas = partidaRepository.findAllPageable(pag);
        assertNotEquals(partidaspaginadas.size(), numPartidas);
    }

    @Test
    public void findPartidasActivasTest(){
        List<Partida> partidasActivas = partidaRepository.findPartidasActivas();
        assertNotNull(partidasActivas);
        assertFalse(partidasActivas.size()==0);
    }

    @Test
    public void findAllFaccionTypeTest(){
        List<FaccionType> facciones = partidaRepository.findAllFaccionType();
        assertNotNull(facciones);
        assertFalse(facciones.isEmpty());
    }

    @Test
    public void findFaccionTypeByNameTest(){
        FaccionType faccion = partidaRepository.findFaccionTypeByName("Leal");
        assertNotNull(faccion);
        assertEquals("Leal", faccion.toString());
    }

    @Test
    public void findFaccionTypeByNameFailTest(){
        FaccionType faccion = partidaRepository.findFaccionTypeByName("Leal");
        assertNotNull(faccion);
        assertNotEquals("Traidor", faccion.toString());
    }
}
