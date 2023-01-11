package org.springframework.samples.petclinic.estadisticas;

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
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.samples.petclinic.estadistica.DificultadType;
import org.springframework.samples.petclinic.estadistica.Logro;
import org.springframework.samples.petclinic.estadistica.LogroController;
import org.springframework.samples.petclinic.estadistica.LogroService;
import org.springframework.samples.petclinic.estadistica.LogrosType;

@WebMvcTest(controllers = LogroController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class LogrosControllerTest {
    @Autowired
    LogroController logroController;

    @MockBean
    LogroService logroService;

    @Autowired
	MockMvc mockMvc;

    Logro logro = new Logro();

    @BeforeEach
    void config(){
        logro.setId(3423);
        logro.setDescripcion("Logro");
        DificultadType df = new DificultadType();
        df.setId(4534);
        df.setName("Easy");
        logro.setDificultad(df);
        logro.setLimite(1);
        logro.setNombre("Fifa");
        LogrosType lt = new LogrosType();
        lt.setId(1214);
        lt.setName("Tipo1");
        logro.setTipo(lt);
    }

    @WithMockUser(value = "spring")
    @Test
    public void testInitCreationForm()throws Exception{
        mockMvc.perform(get("/logros/new")).andExpect(status().isOk())
        .andExpect(model().attributeExists("logro"));
    }

    @WithMockUser(value = "Spring")
    @Test
    public void testCreationLogro() throws Exception{
        mockMvc.perform(post("/logros/new")
        .param("nombre", "El logro")
        .param("descripción","No tiene")
        .with(csrf()))
        .andExpect(status().is2xxSuccessful());
    }

    @WithMockUser(value = "spring")
    @Test
    public void testEditForm()throws Exception{
        mockMvc.perform(get("/logros/edit/1")).andExpect(status().isOk());
        mockMvc.perform(post("/logros/edit/1")
        .param("nombre", "Logro Editado")
        .with(csrf()))
        .andExpect(status().is3xxRedirection());
    }
    
}
