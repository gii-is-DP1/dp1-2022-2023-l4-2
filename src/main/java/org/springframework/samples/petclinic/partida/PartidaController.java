package org.springframework.samples.petclinic.partida;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public static final String PARTIDAS_FINAL = "partidas/finalPartida";
    public static final String EDIL_JUGAR = "partidas/edilPartida";
    public static final String CONSUL_JUGAR = "partidas/consulPartida";
    public static final String PRETOR_JUGAR = "partidas/pretorPartida";
    public static final String PRETOR_EDIT = "partidas/pretorEdit";
    public static final String ESCOGER_PRETOR = "partidas/escogerPretor";
    public static final String ESCOGER_EDIL = "partidas/escogerEdil";


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
        if(j.getParticipacionEnPartida(p) == null){
            creaParticipacion(j, p);
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
        if(j.getParticipacionEnPartida(p) == null){
            creaParticipacion(j, p);
        }
        Map<Jugador,List<FaccionType>> map = partidaService.jugadoresConOpcionesDePartida(p);
        for(Jugador jugador:p.getJugadores()){
            Participacion part = jugador.getParticipacionEnPartida(p);
            part.setOpciones(map.get(jugador));
        }
        
        if(p.getParticipaciones().size() == p.getNumJugadores()){
            reparteRoles(p);
        }
        return "redirect:/partidas/jugar/{id}";
    }
    public void creaParticipacion(Jugador j, Partida p){
        Participacion part = new Participacion();
        Integer idp = participacionService.getParticipaciones().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        part.setId(idp+1);
        if(p.getParticipaciones().isEmpty()){
            part.setNumConsul(1);
        }else{
            Integer maxNumConsul = p.participaciones.stream().map(x->x.getNumConsul()).max(Comparator.comparing(x->x)).orElse(1);
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
    }


    @GetMapping("/jugar/{id}")
    public ModelAndView jugarPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(PARTIDAS_JUGAR);
        response.addHeader("Refresh", "20");
        Partida p = partidaService.getPartidaById(id).get();
        //List<FaccionType> faccionesApoyadas = p.getParticipaciones().stream().map(x->x.getFaccionApoyada()).collect(Collectors.toList());
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Integer numVotos = votoService.getVotosTurnoJugador(p, j).size();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        FaccionType faccionApoyada = j.getParticipacionEnPartida(p).getFaccionApoyada();
        Boolean hayConsul = null;
        hayConsul = p.getJugadores().stream().map(x->x.getRol().getName()).anyMatch(x-> x.equals("Pretor"));
        Integer numEdil = p.getJugadores().stream().filter(x -> x.getRol().getName().equals("Edil")).collect(Collectors.toList()).size();
        result.addObject("numEdil", numEdil);
        result.addObject("hayConsul", hayConsul);
        result.addObject("faccionGanadora", p.getFaccionGanadora());
        result.addObject("faccionApoyada", faccionApoyada);
        result.addObject("elegir", elegir);
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        result.addObject("numVotos", numVotos);
        return result;
    }

    @GetMapping("/final/{id}")
    public ModelAndView finalPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(PARTIDAS_FINAL);
        response.addHeader("Refresh", "20");
        Partida p = partidaService.getPartidaById(id).get();
        //List<FaccionType> faccionesApoyadas = p.getParticipaciones().stream().map(x->x.getFaccionApoyada()).collect(Collectors.toList());
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Integer numVotos = votoService.getVotosTurnoJugador(p, j).size();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        FaccionType faccionApoyada = j.getParticipacionEnPartida(p).getFaccionApoyada();
        result.addObject("faccionGanadora", p.getFaccionGanadora());
        result.addObject("faccionApoyada", faccionApoyada);
        result.addObject("elegir", elegir);
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        result.addObject("numVotos", numVotos);
        return result;
    }

    @GetMapping("/jugar/edil/{id}")
    public ModelAndView partidaEdil(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(EDIL_JUGAR);
        //response.addHeader("Refresh", "2");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType ft = new FaccionType();
        Integer maxid = partidaService.getFaccionesType().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        ft.setId(maxid+1);
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        FaccionType faccionApoyada = j.getParticipacionEnPartida(p).getFaccionApoyada();
        result.addObject("faccionApoyada", faccionApoyada);
        result.addObject("elegir", elegir);
        result.addObject("faccionType", ft);
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        return result;
    }

    
    @PostMapping("/jugar/edil/{id}")
    public ModelAndView guardarVoto(@PathVariable("id") Long id, @Valid FaccionType ft, BindingResult br, Principal principal){
        //if(br.hasErrors()){
        //    return new ModelAndView(EDIL_JUGAR,br.getModel());
        //}
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType faccion = partidaService.getFaccionesTypeByName(ft.getName());
        Partida p = partidaService.getPartidaById(id).get();
        Integer maxVoto = votoService.getVotos().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
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
        response.addHeader("Refresh", "10");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Voto> votos = votoService.getVotosRondaTurno(p);
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        FaccionType faccionApoyada = j.getParticipacionEnPartida(p).getFaccionApoyada();
        result.addObject("faccionApoyada", faccionApoyada);
        result.addObject("elegir", elegir);
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        result.addObject("principal", principal);
        result.addObject("votos", votos);
        return result;
    }
    @GetMapping("/jugar/pretor/edit/{partidaId}/{votoId}")
    public ModelAndView pretorEditVoto(@PathVariable("partidaId") Long partidaId, @PathVariable("votoId") Long votoId,
                                HttpServletResponse response, Principal principal){
        ModelAndView res =new ModelAndView(PRETOR_EDIT);
        //response.addHeader("Refresh", "10");
        Partida p = partidaService.getPartidaById(partidaId).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Voto v = votoService.getVotoById(votoId).get();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        FaccionType faccionApoyada = j.getParticipacionEnPartida(p).getFaccionApoyada();
        res.addObject("faccionApoyada", faccionApoyada);
        res.addObject("elegir", elegir);
        res.addObject("voto", v);
        res.addObject("jugadorLog", j);
        res.addObject("partida", p);
        res.addObject("principal", principal);
        return res;

    }

    @PostMapping("/jugar/pretor/edit/{partidaId}/{votoId}")
    public String partidaPretor(@PathVariable("partidaId") Long partidaId,@PathVariable("votoId") Long votoId,
                                    @Valid Voto voto,HttpServletResponse response, Principal principal){
        response.addHeader("Refresh", "10");
        Partida p = partidaService.getPartidaById(partidaId).get();
        Voto votoToUpdate = votoService.getVotoById(votoId).get();
        votoToUpdate.setFaccion(voto.getFaccion());
        //Voto votoCambiado = cambiarVoto(voto);
        votoService.saveVoto(votoToUpdate);
        List<Voto> votos = votoService.getVotosRondaTurno(p);
        for(Voto v: votos){
            Participacion participacion = v.getJugador().getParticipacionEnPartida(p);

            if(v.getFaccion().getName().equals("Traidor")){
                p.setVotosContraCesar(p.getVotosContraCesar()+1);
                participacion.setVotosContraCesar(participacion.getVotosContraCesar()+1);
            }else{
                p.setVotosFavorCesar(p.getVotosFavorCesar()+1);
                participacion.setVotosFavorCesar(participacion.getVotosFavorCesar()+1);
            }
            participacionService.save(participacion);
        }
        if(p.getTurno() !=1 &&p.getRonda()==1){
            p.setFase(1);
        }
        if(p.getTurno() == 1 && p.getRonda()==1){
            p.setTurno(p.getTurno()+1);
            reparteRoles(p);
        }
        
        partidaService.save(p);
        return "redirect:/partidas/jugar/{partidaId}";
    }
    
    public Voto cambiarVoto(Voto v){
        if(v.getFaccion().getName() == "Leal"){
            v.setFaccion(partidaService.getFaccionesTypeByName("Traidor"));
        }else{
            v.setFaccion(partidaService.getFaccionesTypeByName("Leal"));
        }
        return v;
    }


    @GetMapping("/jugar/consul/{id}")
    public ModelAndView partidaConsul(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(CONSUL_JUGAR);
        //response.addHeader("Refresh", "2");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType ft = new FaccionType();
        Integer maxid = partidaService.getFaccionesType().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        ft.setId(maxid+1);
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        
        result.addObject("faccionType", ft);
        result.addObject("opciones", opciones);
        result.addObject("jugadorLog", j);
        result.addObject("partida", p);
        return result;
    }

    @GetMapping("/jugar/consul/eleccionP/{id}")
    public ModelAndView escogerPretor(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView res = new ModelAndView(ESCOGER_PRETOR);
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Jugador> jugadores = p.getJugadores();
        List<Jugador> jugadoresFiltrado = jugadores.stream().filter(x-> x.getRol() != null).filter(x-> !x.getRol().getName().equals("Pretor")).filter(x-> !x.getRol().getName().equals("Consul")).filter(x-> x.getYaElegido() != true).collect(Collectors.toList());
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        res.addObject("opciones", opciones);
        res.addObject("jugadorLog", j);
        res.addObject("partida", p);
        res.addObject("jugFilt", jugadoresFiltrado);
        return res;
    }

    @PostMapping("/jugar/consul/eleccionP/{id}")
    public ModelAndView seleccionarPretor(@PathVariable("id") Long id, @Valid Jugador jugador, BindingResult br, Principal principal){
        List<RolType> roles = jugadorService.getRoles();
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorById(jugador.getId()).get();
        RolType pretor= roles.stream().filter(x->x.getName().equals("Pretor")).findAny().get();
        j.setRol(pretor);
        j.setYaElegido(true);
        jugadorService.save2(j);

        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        return res;
        
    }

    @GetMapping("/jugar/consul/eleccionE/{id}")
    public ModelAndView escogerEdil(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView res = new ModelAndView(ESCOGER_PRETOR);
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Jugador> jugadores = p.getJugadores();
        List<Jugador> jugadoresFiltrado = List.of();
        if (p.getNumJugadores() == 5) {
            jugadoresFiltrado = jugadores.stream().filter(x-> x.getRol() != null).filter(x-> !x.getRol().getName().equals("Consul")).filter(x-> x.getYaElegido() != true).collect(Collectors.toList());
        } else {
            jugadoresFiltrado = jugadores.stream().filter(x-> x.getRol() != null).filter(x-> !x.getRol().getName().equals("Edil")).filter(x-> !x.getRol().getName().equals("Consul")).filter(x-> x.getYaElegido() != true).collect(Collectors.toList());
        }
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        res.addObject("opciones", opciones);
        res.addObject("jugadorLog", j);
        res.addObject("partida", p);
        res.addObject("jugFilt", jugadoresFiltrado);
        return res;
    }

    @PostMapping("/jugar/consul/eleccionE/{id}")
    public ModelAndView seleccionarEdil(@PathVariable("id") Long id, @Valid Jugador jugador, BindingResult br, Principal principal){
        List<RolType> roles = jugadorService.getRoles();
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorById(jugador.getId()).get();
        RolType edil= roles.stream().filter(x->x.getName().equals("Edil")).findAny().get();
        j.setRol(edil);
        j.setYaElegido(true);
        jugadorService.save2(j);

        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        return res;
        
    }


    @PostMapping("/jugar/consul/{id}")
    public ModelAndView guardarFaccion(@PathVariable("id") Long id, @Valid FaccionType ft, BindingResult br, Principal principal){
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType faccion = partidaService.getFaccionesTypeByName(ft.getName());
        Partida p = partidaService.getPartidaById(id).get();
        Participacion participacion = j.getParticipacionEnPartida(p);
        participacion.setFaccionApoyada(faccion);
        p.setFase(0);
        
        p.setTurno(p.getTurno()+1);
        reparteRoles(p);
        if(p.getTurno()>p.getNumJugadores()){
            p.setRonda(p.getRonda()+1);
            p.setTurno(1);
            preparaRolesRonda2(p);
        }
        if(p.getTiempo() == 0 && p.getRonda() == 3){
            p.setFaccionGanadora(p.calculoFaccionGanadora());
            p.setTiempo(p.calculaTiempoFinal(p.getFechaInicio()));
        }
        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        partidaService.save(p);
        participacionService.save(participacion);
        
        
        return res;
        
    }
    public void preparaRolesRonda2(Partida p){
        List<RolType> roles = jugadorService.getRoles();
        RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
        RolType sinRol = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
        
        for(int i = 0;i<p.getJugadores().size();i++){
            Jugador j = p.getJugadores().get(i);
            if(j.getParticipacionEnPartida(p).getNumConsul() == p.getTurno()){
                j.setRol(consul);
            }else{
                j.setRol(sinRol);
            }
            jugadorService.save2(j);
        }
    }


    public void reparteRoles(Partida p){
        List<RolType> roles = jugadorService.getRoles();
        for(int i=0;i<p.getJugadores().size();i++){
            Jugador j =p.getJugadores().get(i);
            Participacion part = j.getParticipacionEnPartida(p);
            RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
            RolType pretor= roles.stream().filter(x->x.getName().equals("Pretor")).findAny().get();
            RolType edil = roles.stream().filter(x->x.getName().equals("Edil")).findAny().get();
            RolType sinRol = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
            if(part.getNumConsul()== p.getTurno()){
                j.setRol(consul);
            }else if(part.getNumConsul() == (p.getTurno()+1)%p.getNumJugadores()){
                j.setRol(pretor);
            }else if(part.getNumConsul() == (p.getTurno()+2)%p.getNumJugadores() ||part.getNumConsul() == (p.getTurno()+3)%p.getNumJugadores()){
                j.setRol(edil);
            }else{
                j.setRol(sinRol);
            }

            if(part.getNumConsul() == p.getNumJugadores()){
                if(part.getNumConsul() == p.getTurno()){
                    j.setRol(consul);
                }else if(part.getNumConsul()-1 == p.getTurno()){
                    j.setRol(pretor);
                }else if(part.getNumConsul()-2 == p.getTurno() || part.getNumConsul()-3 == p.getTurno()){
                    j.setRol(edil);
                }else{
                    j.setRol(sinRol);
                }
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
        partida.setFechaInicio(LocalDateTime.now());
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
