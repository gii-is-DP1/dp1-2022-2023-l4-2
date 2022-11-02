package org.springframework.samples.petclinic.jugador;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

public static final String JUGADORES_LISTING = "jugadores/jugadoresList";
public static final String JUGADOR_CREATE = "jugadores/jugadorCreate";

private JugadorService jugadorService;

@Autowired
public JugadorController(JugadorService jugadorService){
    this.jugadorService = jugadorService;
}

@GetMapping()
public ModelAndView showJugadores() {
    ModelAndView result = new ModelAndView(JUGADORES_LISTING);
    result.addObject("jugadores", jugadorService.getJugadores());
    return result;
}

@GetMapping("/delete/{id}")
public ModelAndView deleteJugador(@PathVariable("id") long id){
    jugadorService.deleteJugador(id);
    return new ModelAndView("redirect:/jugadores");
}

@GetMapping("/new")
public String  initCreatinForm(Map<String, Object> model){
    Jugador j = new Jugador();
    model.put("jugador", j);
    return JUGADOR_CREATE;
}

@PostMapping("/new")
public String processCreationForm(@Valid Jugador j, BindingResult br){
    if(br.hasErrors()){
        return JUGADOR_CREATE;
    }else{
        this.jugadorService.saveJugador(j);

        return "welcome";
    }
}

}
