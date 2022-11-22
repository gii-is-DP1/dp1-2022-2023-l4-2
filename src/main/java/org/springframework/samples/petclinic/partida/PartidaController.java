package org.springframework.samples.petclinic.partida;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.Timer;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.jugador.RolType;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    public static final String PARTIDAS_LISTING = "partidas/partidasList";
    public static final String PARTIDAS_SELECCIONAR = "partidas/partidasSelect";
    public static final String PARTIDAS_CREAR = "partidas/partidaCreate";
    public static final String PARTIDAS_DISPONIBLES = "partidas/partidasDisponibles";
    public static final String PARTIDAS_UNIR = "partidas/partidaJoin";
    public static final String PARTIDAS_JUGAR = "partidas/partida";
    public static final String EDIL_JUGAR = "partidas/edilPartida";
    public static final String CONSUL_JUGAR = "partidas/consulPartida";
    public static final String PRETOR_JUGAR = "partidas/pretorPartida";


    private PartidaService partidaService;

    @Autowired
    public PartidaController(PartidaService partidaService){
        this.partidaService = partidaService;
    }
    @Autowired
    public JugadorService jugadorService;

    @Autowired
    public ParticipacionService participacionService;

    @Autowired
    public VotoService votoService;

    @GetMapping()
	public ModelAndView showPartidas(@RequestParam("page") int page) {
		ModelAndView result = new ModelAndView(PARTIDAS_LISTING);
        Pageable request = PageRequest.of(page,5);
		result.addObject("partidas", partidaService.getPartidasPageables(request));
        Integer partidas = partidaService.getPartidas().size();
        Integer i = Integer.valueOf(partidas/5);
        if(i%2==0){
            i --;
        }
        result.addObject("num", i);
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


        //if(p.getRonda()!=0 && p.getActiva()){
        //    result =new ModelAndView("redirect:/partidas/jugar/{id}");
        //}
        if(!p.getJugadores().contains(jugadorService.getJugadorByUsername(p.getAnfitrion()))){
           result =new ModelAndView("redirect:/partidas/join/");
           partidaService.deletePartida(p.getId());
        }

        if(jugadores.size() == p.getNumJugadores()){
            return new ModelAndView("redirect:/partidas/iniciar/{id}");
        }
        if(!p.getJugadores().contains(j)){
            jugadores.add(j);
            p.setJugadores(jugadores);
            partidaService.edit(p);
        }
        if(!j.getPartidas().contains(p)){
            j.getPartidas().add(p);
            jugadorService.saveJugador(j);
        }

        result.addObject("jugadores", jugadores);
        result.addObject("partida", p);
        result.addObject("jugadorActual", j);
        return result;
    }

    @GetMapping("/join")
    public ModelAndView partidasDisponibles(HttpServletResponse response, Principal principal){
        response.addHeader("Refresh", "4");
        List<Partida> partidas = partidaService.getPartidasActivas();
        ModelAndView result = new ModelAndView(PARTIDAS_DISPONIBLES);
        for(Partida p : partidas){
            Jugador j = jugadorService.getJugadorByUsername(principal.getName());
            if(!p.getJugadores().contains(jugadorService.getJugadorByUsername(p.getAnfitrion()))){
                p.setActiva(false);
                partidaService.edit(p);
            }
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

    RolType edil = new RolType();
    RolType consul = new RolType();
    RolType pretor = new RolType();

    @GetMapping("/iniciar/{id}")
    public String iniciarPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        //ModelAndView result = new ModelAndView(PARTIDAS_JUGAR);
        //response.addHeader("Refresh", "2");
        Partida p = partidaService.getPartidaById(id).get();
        p.setTurno(1);
        p.setRonda(1);
        Jugador j =jugadorService.getJugadorByUsername(principal.getName());
        Integer idp = participacionService.getParticipaciones().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).get();
        Participacion part = new Participacion();
        part.setId(idp+1);
        if(p.getParticipaciones().isEmpty()){
            part.setNumConsul(0);
        }else{
            Integer maxNumConsul = p.participaciones.stream().map(x->x.getNumConsul()).max(Comparator.comparing(x->x)).get();
            part.setNumConsul(maxNumConsul+1);
        }
        if(p.getAnfitrion() == j.getUser().getUsername()){
        part.setEsAnfitrion(true);
        }else{
            part.setEsAnfitrion(false);
        }
        part.setVotosContraCesar(0);
        part.setVotosFavorCesar(0);
        part.setVotosNeutros(0);
        part.setPartidas(p);
        List<FaccionType> aux = partidaService.jugadoresConOpcionesDePartida(p).get(j);
        part.setFaccionApoyada(null);
        part.setOpciones(aux);
        List<Participacion> ls2 = p.getParticipaciones();
        List<Participacion> ls = j.getParticipaciones();
        participacionService.save(part);
        ls.add(part);
        ls2.add(part);
        j.setParticipaciones(ls);
        jugadorService.editJugador(j);
        p.setParticipaciones(ls2);
        partidaService.save(p);
        if(p.getParticipaciones().size() == p.getNumJugadores()){
            reparteRoles(p);
        }
        return "redirect:/partidas/jugar/{id}";
    }


    @GetMapping("/jugar/{id}")
    public ModelAndView jugarPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(PARTIDAS_JUGAR);
        response.addHeader("Refresh", "20");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        return result;
    }

    @GetMapping("/jugar/edil/{id}")
    public ModelAndView partidaEdil(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(EDIL_JUGAR);
        //response.addHeader("Refresh", "2");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        return result;
    }

    
    @PostMapping("/jugadr/edil/{id}")
    public ModelAndView guardarVoto(@PathVariable("id") Long id, @Valid FaccionType ft, BindingResult br, Principal principal){
        //if(br.hasErrors()){
        //    return new ModelAndView(EDIL_JUGAR,br.getModel());
        //}
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType faccion = partidaService.getFaccionesTypeByName(ft.getName()).get(0);
        Partida p = partidaService.getPartidaById(id).get();
        Integer maxVoto = votoService.getVotos().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).get();
        Voto v = new Voto();

        v.setId(maxVoto+1);
        v.setFaccion(faccion);
        v.setJugador(j);
        v.setPartida(p);
        v.setRonda(p.getRonda());
        v.setTurno(p.getTurno());
        votoService.saveVoto(v);

        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        return res;
        
    }

    @GetMapping("/jugar/pretor/{id}")
    public ModelAndView partidaPretor(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(PRETOR_JUGAR);
        //response.addHeader("Refresh", "2");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        return result;
    }
    
    @GetMapping("/jugar/consul/{id}")
    public ModelAndView partidaConsul(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(CONSUL_JUGAR);
        //response.addHeader("Refresh", "2");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        result.addObject("opciones", opciones);
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        return result;
    }


    public void reparteRoles(Partida p){
        List<RolType> roles = jugadorService.getRoles();
        for(int i=0;i<p.getJugadores().size();i++){
            Jugador j =p.getJugadores().get(i);
            Participacion part = j.getParticipacionEnPartida(p);
            if(part.getNumConsul()== p.getTurno()){
                j.setRol(roles.stream().filter(x->x.getName().equals("Consul")).findAny().get());
            }else if(part.getNumConsul() == p.getTurno()+1%p.getNumJugadores()){
                j.setRol(roles.stream().filter(x->x.getName().equals("Pretor")).findAny().get());
            }else if(part.getNumConsul() == p.getTurno()+2%p.getNumJugadores() ||part.getNumConsul() == p.getTurno()+3%p.getNumJugadores()){
                j.setRol(roles.stream().filter(x->x.getName().equals("Edil")).findAny().get());
            }else{
                j.setRol(roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get());
            }
            jugadorService.save2(j);
        }
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
        partida.setFase(0);
        partidaService.save(partida);
        ModelAndView result =new ModelAndView("redirect:/partidas/join/"+partida.getId());
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
