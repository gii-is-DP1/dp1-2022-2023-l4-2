package org.springframework.samples.petclinic.jugador;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.estadistica.Logro;
import org.springframework.samples.petclinic.estadistica.LogroService;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;



@WebMvcTest(controllers = JugadorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class JugadorControllerTests {
    
    @Autowired
    JugadorController jugadorController;

    @MockBean
    PartidaService partidaService;

    @Autowired
    @MockBean
    LogroService logroService;

    @Autowired
    @MockBean
    JugadorService jugadorService;


    @Autowired
	MockMvc mockMvc;

    Jugador jugador;
    List<Logro> logros;


    @BeforeEach
    void config(){{
        jugador = jugadorService.getJugadorByUsername("Guaje");
        logros = logroService.getLogros();
    }
}

    @WithMockUser(value = "spring")
    @Test
    public void testInitCreationForm()throws Exception{
        mockMvc.perform(get("/jugadores/new")).andExpect(status().isOk())
        .andExpect(model().attributeExists("jugador"));
    }

    @WithMockUser(value = "Spring")
    @Test
    public void testCreationJugador() throws Exception{
        mockMvc.perform(post("/jugadores/new")
        .param("usuario", "El jugador")
        .param("amigoDe","No tiene ")
        .with(csrf()))
        .andExpect(status().is2xxSuccessful());
    }

    @WithMockUser(value = "spring")
    @Test
    public void testEditForm()throws Exception{
        mockMvc.perform(get("/jugadores/editPerfil/Colombiano")).andExpect(status().isOk());
        mockMvc.perform(post("/jugadores/editPerfil/Colombiano")
        .param("usuario", "Pablito")
        .with(csrf()))
        .andExpect(status().is3xxRedirection());
    }

    
}
