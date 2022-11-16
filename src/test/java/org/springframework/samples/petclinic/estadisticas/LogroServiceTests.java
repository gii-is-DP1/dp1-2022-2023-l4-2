package org.springframework.samples.petclinic.estadisticas;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.samples.petclinic.estadistica.DificultadType;
import org.springframework.samples.petclinic.estadistica.Logro;
import org.springframework.samples.petclinic.estadistica.LogroService;
import org.springframework.samples.petclinic.estadistica.LogrosType;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class LogroServiceTests {


    @Autowired
    protected LogroService logroService;
    DificultadType dt;
    LogrosType lt;

    @Test
    void shouldGetLogros(){
        List<Logro> logros = logroService.getLogros();

        assertNotNull(logros);
        assertFalse(logros.isEmpty());
    }

    @Test
    void shouldFindById(){
        List<Logro> logros = logroService.getLogros();
        Logro logro = logros.get(0);
        Logro find = logroService.getLogroById(1).get();
        assertEquals(logro, find);
    }

    @Test
    void negativeFindById(){
        assertThrows(NoSuchElementException.class, () -> logroService.getLogroById(-1).get());
    }

    @Test
    void shouldDeleteById(){
        List<Logro> logros = logroService.getLogros();
        Logro logro = logros.get(0);
        logroService.deleteLogro(logro.getId());
        List<Logro> logros2 = logroService.getLogros();
        assertFalse(logros.size() == logros2.size()-1);
        assertNotEquals(logros.get(0), logros2.get(0));
    }

    @Test
    void negativeDeleteById(){
        assertThrows(EmptyResultDataAccessException.class, () -> logroService.deleteLogro(-1));
        assertThrows(EmptyResultDataAccessException.class, () -> logroService.deleteLogro(102));      
    }

    @Test
    void shouldSaveLogro(){
        List<Logro> logros = logroService.getLogros();
        Logro logro = logros.get(0);
        assertEquals(logro.getId(), 1);
        logro.setId(2);
        logroService.save(logro);
        assertEquals(logro.getId(), 2);
    }

    @Test
    void negativeSaveLogro(){
        Logro l = null;
        assertThrows(InvalidDataAccessApiUsageException.class, () -> logroService.save(l)); 
    }



    
}
