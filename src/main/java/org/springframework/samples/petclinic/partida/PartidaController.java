package org.springframework.samples.petclinic.partida;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    public static final String PARTIDAS_LISTING = "partidas/partidasList";
    public static final String PARTIDAS_SELECCIONAR = "partidas/partidasSelect";
    public static final String PARTIDAS_CREAR = "partidas/partidaCreate";
    public static final String PARTIDAS_DISPONIBLES = "partidas/partidasDisponibles";
    public static final String PARTIDAS_UNIR = "partidas/partidaJoin";
    private PartidaService partidaService;

    @Autowired
    public PartidaController(PartidaService partidaService){
        this.partidaService = partidaService;
    }
    @Autowired
    public JugadorService jugadorService;

    @GetMapping()
	public ModelAndView showPartidas() {
		ModelAndView result = new ModelAndView(PARTIDAS_LISTING);
		result.addObject("partidas", partidaService.getPartidas());
		return result;
	}

    @GetMapping("/seleccionar")
	public ModelAndView tipoPartida() {
		ModelAndView result = new ModelAndView(PARTIDAS_SELECCIONAR);
		return result;
	}

    @GetMapping("/new")
    public ModelAndView createPartida(){
        Partida p = new Partida();
        ModelAndView result = new ModelAndView(PARTIDAS_CREAR);
        result.addObject("partida", p);
        return result;
    }

    @GetMapping("/join/{id}")
    public ModelAndView joinPartida(@PathVariable("id") Long id, Principal principal, HttpServletResponse response){
        response.addHeader("Refresh", "4");
        ModelAndView result = new ModelAndView(PARTIDAS_UNIR);
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Partida p = partidaService.getPartidaById(id).get();
        List<Jugador> jugadores = p.getJugadores();

        if(!p.getJugadores().contains(j)){
            jugadores.add(j);
            p.setJugadores(jugadores);
            partidaService.edit(p);
        }

        result.addObject("jugadores", jugadores);
        result.addObject("partida", p);
        result.addObject("jugadorActual", j);
        return result;
    }

    @GetMapping("/join")
    public ModelAndView partidasDisponibles(HttpServletResponse response, Principal principal){
        response.addHeader("Refresh", "4");
        List<Partida> partidas = partidaService.getPartidas();
        ModelAndView result = new ModelAndView(PARTIDAS_DISPONIBLES);
        for(Partida p : partidas){
            Jugador j = jugadorService.getJugadorByUsername(principal.getName());
            if(p.getJugadores().contains(j)){
                List<Jugador> jugadores = p.getJugadores();
                jugadores.remove(j);
                p.setJugadores(jugadores);
                partidaService.edit(p);
            }
        }
        result.addObject("partidas", partidas);
        return result;
    }

    @PostMapping("/new")
    public ModelAndView saveNewPartida(@Valid Partida partida, BindingResult br, Principal principal){
        if(br.hasErrors()){
            return new ModelAndView(PARTIDAS_CREAR,br.getModel());
        }
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Jugador> ls = List.of(j);
        partida.setRonda(0);
        partida.setTurno(0);
        partida.setTiempo(0);
        partida.setVotosContraCesar(0);
        partida.setVotosFavorCesar(0);
        partida.setFaccionGanadora(null);
        partida.setParticipaciones(new ArrayList<>());
        partida.setJugadores(ls);
        partida.setAnfitrion(j.getUser().getUsername());
        partida.setLimite(calculaLimite(partida.getNumJugadores()));
        partida.setActiva(true);
        partidaService.save(partida);
        ModelAndView result =new ModelAndView("redirect:/partidas/join");
        //result.addObject("message", "Ha habido un error creando la partida.");
        return result;
    }

    private Long calculaLimite(Long nJugadores){
        Long res=0l;
        if(nJugadores == 5){
            res = 13l;
        }else if(nJugadores == 6){
            res = 15l;
        }else if(nJugadores == 7){
            res = 17l;
        }else{
            res = 20l;
        }
        return res; 
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePartida(@PathVariable("id") long id){
        partidaService.deletePartida(id);
        return new ModelAndView("redirect:/partidas");
    }

}
