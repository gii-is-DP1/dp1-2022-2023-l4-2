package org.springframework.samples.petclinic.jugador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.PartidaController;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

public static final String JUGADORES_LISTING = "jugadores/jugadoresList";
private final String JUGADOR_FORM="/jugadores/createOrUpdateJugadorForm";

private JugadorService jugadorService;

@Autowired
public JugadorController(JugadorService jugadorService){
    this.jugadorService = jugadorService;
}

@GetMapping()
public ModelAndView showJugadores() {
    ModelAndView result = new ModelAndView(JUGADORES_LISTING);
    result.addObject("partidas", jugadorService.getJugadores());
    return result;
}

@GetMapping("/delete/{id}")
public ModelAndView deleteJugador(@PathVariable("id") long id){
    jugadorService.deleteJugador(id);
    return new ModelAndView("redirect:/jugadores");
}

@GetMapping("/edit/{id}")
    public ModelAndView editLogro(@PathVariable("id") long id){
        ModelAndView result = new ModelAndView(JUGADOR_FORM);
        Optional<Jugador> jugador = jugadorService.getLogroById(id);
        if(jugador.isPresent()){
            result.addObject("logro", jugador.get());
        }else{
            result=showJugadores();
            result.addObject("message", "Room with id "+ id + " not foudn");
        }
        return result;
    }
    
}
