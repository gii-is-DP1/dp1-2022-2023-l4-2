package org.springframework.samples.petclinic.partida;
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
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




@WebMvcTest(controllers = PartidaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class PartidaControllerTests {
    
    @Autowired
    PartidaController partidaController;

    @MockBean
    PartidaService partidaService;

    @MockBean
    ParticipacionService participacionService;

    @MockBean
    VotoService votoService;

    @MockBean
    JugadorService jugadorService;

    @Autowired
	MockMvc mockMvc;

    Partida p = new Partida();
    Jugador j = new Jugador();

    @BeforeEach
    void config(){
        p.setId(150);
        p.setActiva(true);
        p.setAnfitrion("Colombiano");
        FaccionType fp = new FaccionType();
        fp.setId(1);
        p.setFaccionGanadora(fp);
        p.setFase(1);
        p.setLimite(15);
        p.setNumJugadores(6);
        p.setRonda(1);
        p.setTiempo(5);
        p.setTurno(5);
        p.setVotosContraCesar(4);
        p.setVotosFavorCesar(3);
    }

    @WithMockUser(value = "spring")
    @Test
    public void testInitCreationForm()throws Exception{
        mockMvc.perform(get("/partidas/new")).andExpect(status().isOk())
        .andExpect(model().attributeExists("partida"));
    }

    @WithMockUser(value = "Spring")
    @Test
    public void testCreationPartida() throws Exception{
        mockMvc.perform(post("/partidas/new")
        .param("jugadores", "Nuevos jugadores")
        .param("activa","No está activa")
        .with(csrf()))
        .andExpect(status().is2xxSuccessful());
    }

}
