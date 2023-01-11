package org.springframework.samples.petclinic.jugador;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;

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
import org.mockito.Mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;


import org.springframework.samples.petclinic.user.User;

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

    /*@BeforeEach
    void config(){
        jugador.setId(1);
        jugador.setAmigoDe(null);
        jugador.setCreator(null);
        User u = new User();
        u.setUsername("Guaje");
        u.setPassword("1324134");
        jugador.setUser(u);
        jugador.setFirstName("Pablo");
        jugador.setLastName("Mera");
        jugador.setEstaEnPartida(false);
        RolType rt = new RolType();
        rt.setId(1);
        rt.setName("Consul");
        jugador.setRol(rt);
        jugador.setYaElegido(false);
    }*/

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

    /*@WithMockUser(value = "spring")
	@Test
	void testGetLogrosDelJugador() throws Exception{

        given(logroService.getLogros()).willReturn(logros);
        given(jugadorService.getJugadorByUsername("Guaje")).willReturn(jugador);
    
        mockMvc.perform(get("/jugadores/logros/{username}", "Guaje"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("logros", hasSize(2)))
        .andExpect(model().attribute("partidasJugadas", is(1)))
        .andExpect(model().attribute("partidasGanadas", is(1)))
        .andExpect(model().attribute("victoriasLeal", is(1)))
        .andExpect(model().attribute("victoriasTraidor", is(0)))
        .andExpect(model().attribute("victoriasMercader", is(0)))
        .andExpect(view().name("jugadores/logrosDelJugador"));

	}*/
}
