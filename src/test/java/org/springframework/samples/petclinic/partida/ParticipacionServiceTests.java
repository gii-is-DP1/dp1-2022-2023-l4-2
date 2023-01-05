package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ParticipacionServiceTests {
    
    @Autowired
    protected ParticipacionService participacionService;

    @Test
    public void getParticipacionesTest(){
        List<Participacion> participaciones = participacionService.getParticipaciones();
        assertNotNull(participaciones);
        assertFalse(participaciones.isEmpty());
    }

    @Test
    public void getParticipacionByIdTest(){
        Participacion participacion = participacionService.getParticipacionById(1).orElse(null);
        assertNotNull(participacion);
    }

    @Test
    public void getParticipacionByIdFailTest(){
        Participacion participacion = participacionService.getParticipacionById(565335534).orElse(null);
        assertNull(participacion);
    }
    
    @Test
    public void deleteParticipacionTest(){
        participacionService.deleteParticipacion(1);
        Participacion p = participacionService.getParticipacionById(1).orElse(null);
        assertNull(p);
    }

    @Test
    public void deleteParticipacionFailTest(){
        assertThrows(EmptyResultDataAccessException.class,()->participacionService.deleteParticipacion(345344));
    }

    @Test
    public void saveParticipacionTest(){
        Participacion p = new Participacion();
        Integer size = participacionService.getParticipaciones().size();
        try{
            participacionService.save(p);
            Participacion p2 = participacionService.getParticipacionById(size + 1).orElse(null);
            assertEquals(p, p2);
        }catch(Exception e){
            fail("This expeception should not be thrown!");
        }
    }
}
