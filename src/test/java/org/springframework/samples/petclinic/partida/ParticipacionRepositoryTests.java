package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ParticipacionRepositoryTests {
    
    @Autowired
    protected ParticipacionRepository participacionRepository;

    @Test
    public void findAllTest(){
        List<Participacion> participaciones = participacionRepository.findAll();
        assertNotNull(participaciones);
        assertFalse(participaciones.isEmpty());
    }
}
