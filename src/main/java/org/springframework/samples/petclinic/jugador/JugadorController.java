package org.springframework.samples.petclinic.jugador;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.estadistica.Logro;
import org.springframework.samples.petclinic.estadistica.LogroService;
import org.springframework.samples.petclinic.partida.FaccionType;
import org.springframework.samples.petclinic.partida.Participacion;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
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
public static final String JUGADOR_PERFIL = "jugadores/jugadorPerfil";
public static final String JUGADOR_HISTORIAL = "jugadores/partidasDelJugador";
public static final String JUGADOR_LOGROS = "jugadores/logrosDelJugador";
public static final String JUGADOR_EDITAR_PERFIL = "jugadores/editPerfil";

private JugadorService jugadorService;

@Autowired
public JugadorController(JugadorService jugadorService){
    
    this.jugadorService = jugadorService;

}

@Autowired
private LogroService logroService;

@GetMapping()
public ModelAndView showJugadores() {
    ModelAndView result = new ModelAndView(JUGADORES_LISTING);
    result.addObject("jugadores", jugadorService.getJugadores());
    return result;
}

@GetMapping("/delete/{id}")
public ModelAndView deleteJugador(@PathVariable("id") Integer id){
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

        return "home";
    }
}

    @GetMapping("/perfil/{username}")
    public ModelAndView showPerfil(@PathVariable("username") String username){
        ModelAndView res = new ModelAndView(JUGADOR_PERFIL);
        Jugador j = jugadorService.getJugadorByUsername(username);
        if(j==null){
            res = new ModelAndView("redirect:/exception");
        }else{
            res.addObject("jugador", j);
            res.addObject("numPartidasJugadas", getPartidasJugadas(username));
            res.addObject("numPartidasGanadas", getPartidasGanadas(username));
            res.addObject("victoriasComoLeal", getVictoriasComoLeal(username));
            res.addObject("victoriasComoTraidor", getVictoriasComoTraidor(username));
            res.addObject("victoriasComoMercader", getVictoriasComoMercader(username));
            res.addObject("tiempoJugado", getTiempoJugado(username));
            res.addObject("faccionFavorita", getFaccionFavorita(username));
        }
        return res;
    }

    
    @GetMapping("/partidas/{username}")
    public ModelAndView getPartidasDelJugador(@PathVariable("username") String username){
        ModelAndView res = new ModelAndView(JUGADOR_HISTORIAL);
        List<Partida> aux = jugadorService.getJugadorByUsername(username).getPartidas();
        res.addObject(username);
        res.addObject("historial", aux);
        return res;
    }

    @GetMapping("/logros/{username}")
    public ModelAndView getLogrosDelJugador(@PathVariable("username") String username){
        ModelAndView res = new ModelAndView(JUGADOR_LOGROS);
        List<Logro> aux = logroService.getLogros();
        res.addObject(username);
        res.addObject("logros", aux);
        res.addObject("partidasJugadas", getPartidasJugadas(username));
        res.addObject("partidasGanadas", getPartidasGanadas(username));
        res.addObject("victoriasLeal", getVictoriasComoLeal(username));
        res.addObject("victoriasTraidor", getVictoriasComoTraidor(username));
        res.addObject("victoriasMercader", getVictoriasComoMercader(username));
        return res;
    }

    @GetMapping("/editPerfil/{username}")
    public ModelAndView editPerfilJugador(@PathVariable("username") String username) {
        ModelAndView result = new ModelAndView(JUGADOR_EDITAR_PERFIL);
        Jugador jugador = jugadorService.getJugadorByUsername(username);
        result.addObject(username);
        result.addObject("jugador", jugador);
        return result;
    }

    @PostMapping("/editPerfil/{username}")
    public ModelAndView savePerfilJugador(Jugador jugador, BindingResult br, @PathVariable("username") String username) {
        if (!br.hasErrors()) {
            jugadorService.saveJugador(jugador);
        } else {

        }
        return new ModelAndView("redirect:/jugadores/perfil/{username}");
    }

    private Integer getPartidasJugadas(String username){
        return jugadorService.getJugadorByUsername(username).getPartidas().size();
    }

    private Integer getPartidasGanadas(String username){
        Integer res = 0;
        List<Partida> partidas = jugadorService.getJugadorByUsername(username).getPartidas();
        List<Participacion> participaciones = jugadorService.getJugadorByUsername(username).getParticipaciones();
        for(Partida partida: partidas){
            for(Participacion participacion: participaciones){
                if(partida.getParticipaciones().contains(participacion) && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())){
                    res++;
                }
            }
        }
        return res;
    }

    private Integer getVictoriasComoLeal(String username){
        Integer res = 0;
        List<Partida> partidas = jugadorService.getJugadorByUsername(username).getPartidas();
        List<Participacion> participaciones = jugadorService.getJugadorByUsername(username).getParticipaciones();
        for(Partida partida: partidas){
            for(Participacion participacion: participaciones){
                if(partida.getParticipaciones().contains(participacion) && participacion.getFaccionApoyada().getName().equals("Leal") && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())){
                    res++;
                }
            }
        }
        return res;
    }

    private Integer getVictoriasComoTraidor(String username){
        Integer res = 0;
        List<Partida> partidas = jugadorService.getJugadorByUsername(username).getPartidas();
        List<Participacion> participaciones = jugadorService.getJugadorByUsername(username).getParticipaciones();
        for(Partida partida: partidas){
            for(Participacion participacion: participaciones){
                if(partida.getParticipaciones().contains(participacion) && participacion.getFaccionApoyada().getName().equals("Traidor") && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())){
                    res++;
                }
            }
        }
        return res;
    }

    private Integer getVictoriasComoMercader(String username){
        Integer res = 0;
        List<Partida> partidas = jugadorService.getJugadorByUsername(username).getPartidas();
        List<Participacion> participaciones = jugadorService.getJugadorByUsername(username).getParticipaciones();
        for(Partida partida: partidas){
            for(Participacion participacion: participaciones){
                if(partida.getParticipaciones().contains(participacion) && participacion.getFaccionApoyada().getName().equals("Mercader") && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())){
                    res++;
                }
            }
        }
        return res;
    }

    private Long getTiempoJugado(String username){
        Long res = 0L;
        List<Partida> partidas = jugadorService.getJugadorByUsername(username).getPartidas();
        for(Partida partida: partidas){
            res += partida.getTiempo();
        }
        return res;
    }

    private String getFaccionFavorita(String username){
        Integer leal = 0;
        Integer traidor = 0;
        Integer mercader = 0;
        List<Participacion> participaciones = jugadorService.getJugadorByUsername(username).getParticipaciones();
        for(Participacion participacion: participaciones){
            if(participacion.getFaccionApoyada().getName().equals("Leal")){
                leal++;
            }else if(participacion.getFaccionApoyada().getName().equals("Traidor")){
                traidor++;
            }else{
                mercader++;
            }
        }
        Integer max = Math.max(leal, traidor);
        if(Math.max(max, mercader) == leal){
            return "Leal";
        }else if(Math.max(max, mercader) == traidor){
            return "Traidor";
        }else{
            return "Mercader";
        }
    }


}
