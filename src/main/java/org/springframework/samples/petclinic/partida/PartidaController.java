package org.springframework.samples.petclinic.partida;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.jugador.RolType;
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
    public static final String PARTIDAS_ESPECTAR = "partidas/partidaEspectar";
    public static final String PARTIDAS_FINAL = "partidas/finalPartida";
    public static final String EDIL_JUGAR = "partidas/edilPartida";
    public static final String CONSUL_JUGAR = "partidas/consulPartida";
    public static final String PRETOR_JUGAR = "partidas/pretorPartida";
    public static final String PRETOR_EDIT = "partidas/pretorEdit";
    public static final String ESCOGER_PRETOR = "partidas/escogerPretor";
    public static final String ESCOGER_EDIL = "partidas/escogerEdil";
    public static final String PRETOR_EDIT2 = "partidas/pretorEdit2";
    public static final String EDIL_EDIT = "partidas/edilEdit";

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
        p.setActiva(true);
        Jugador anfitrion = jugadorService.getJugadorByUsername(p.getAnfitrion());
        partidaService.BorraPartidaSiAnfitionSale(p, result, anfitrion);
        if(jugadores.size() == p.getNumJugadores()){
            return new ModelAndView("redirect:/partidas/iniciar/{id}");
        }
        partidaService.añadeJugadorAPartida(j, p);
        jugadorService.añadePartidaAJugador(p,j);
        generarParticipacion(j, p);
        result.addObject("jugadores", jugadores);
        result.addObject("partida", p);
        result.addObject("jugadorActual", j);
        return result;
    }

    public void generarParticipacion(Jugador j,Partida p){
        if(j.getParticipacionEnPartida(p) == null){
            participacionService.creaParticipacion(j, p);
            jugadorService.editJugador(j);
            partidaService.save(p);
        }
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

    @GetMapping("/iniciar/{id}")
    public String iniciarPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(id).get();
        p.setTurno(1);
        p.setRonda(1);
        Jugador j =jugadorService.getJugadorByUsername(principal.getName());
        generarParticipacion(j, p);
        jugadorService.añadePartidaAJugador(p, j);
        Map<Jugador,List<FaccionType>> map = partidaService.jugadoresConOpcionesDePartida(p);
        for(Jugador jugador:p.getJugadores()){
            Participacion part = jugador.getParticipacionEnPartida(p);
            part.setOpciones(map.get(jugador));
        }   
        if(p.getParticipaciones().size() == p.getNumJugadores() && p.getParticipaciones().stream().mapToInt(x->x.getNumConsul()).sum() == 0){
            participacionService.ordenaNumConsul(p);
            jugadorService.reparteRoles(p);
        }
        return "redirect:/partidas/jugar/{id}";
    }

    @GetMapping("/jugar/{id}")
    public ModelAndView jugarPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal) throws Exception{
        response.addHeader("Refresh", "4");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Integer numVotos = votoService.getVotosTurnoJugador(p, j).size();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        ModelAndView result = pasarPropiedadesComunes(PARTIDAS_JUGAR, principal, p, j);
        Boolean hayPretor = null;//Flag para controlar si ya se ha escogida un pretor
        hayPretor = p.getJugadores().stream().filter(x->x.getYaElegido()).map(x->x.getRol().getName()).anyMatch(x-> x.equals("Pretor"));
        Integer numEdil = p.getJugadores().stream().filter(x->x.getYaElegido())
                .filter(x -> x.getRol().getName().equals("Edil"))
                .collect(Collectors.toList()).size();//Flag para saber el numero de ediles escogidos
        List<Voto> votosACambiar = votoService.getVotosElegidosRondaTurno(p, j);
        Voto v = null; //Varialbe para saber si hay algún voto elegido para ser cambiado
        if(!votosACambiar.isEmpty()){
            v = votoService.getVotosElegidosRondaTurno(p, j).get(0);
        }
        Voto votoMercaderE = votoService.getVotoMercaderElegidoRondaTurno(p);//Si hay voto elegido que es de la facción mercader necesitamos tenerlo para representar un mensaje
        Integer votosRT = votoService.getVotosRondaTurno(p).size();//Flag para controlar el numero de votos de esta ronda y turno
        Boolean yaE = j.getYaElegido(); //Comprobamos si un jugador ha sido elegido para un rol
        if(yaE ==null){
            yaE = false;
        }
        result.addObject("yaE", yaE);
        result.addObject("votoMercaderE", votoMercaderE);
        result.addObject("numEdil", numEdil);
        result.addObject("hayPretor", hayPretor);
        result.addObject("faccionGanadora", p.getFaccionGanadora());
        result.addObject("votoRT",votosRT);
        result.addObject("elegir", elegir);
        result.addObject("numVotos", numVotos);
        result.addObject("votosCambio", votosACambiar);
        result.addObject("voto", v);
        return result;
    }

    @GetMapping("/final/{id}")
    public ModelAndView finalPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal) throws Exception{
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Integer numVotos = votoService.getVotosTurnoJugador(p, j).size();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        ModelAndView result = pasarPropiedadesComunes(PARTIDAS_FINAL, principal, p, j);
        result.addObject("faccionGanadora", p.getFaccionGanadora());
        result.addObject("elegir", elegir);
        result.addObject("numVotos", numVotos);
        return result;
    }

    @GetMapping("/espectar/{id}")
    public ModelAndView espectarPartida(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        ModelAndView result = new ModelAndView(PARTIDAS_ESPECTAR);
        response.addHeader("Refresh", "20");
        Partida p = partidaService.getPartidaById(id).get();
        result.addObject("partida", p);
        result.addObject("principal", principal);
        result.addObject("faccionGanadora", p.getFaccionGanadora());
        return result;
    }

    @GetMapping("/jugar/edil/{id}")
    public ModelAndView partidaEdil(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType ft = new FaccionType();
        Integer maxid = partidaService.getFaccionesType().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        ft.setId(maxid+1);
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        ModelAndView result = pasarPropiedadesComunes(EDIL_JUGAR, principal, p, j);
        result.addObject("elegir", elegir);
        result.addObject("faccionType", ft);
        return result;
    }

    
    @PostMapping("/jugar/edil/{id}")
    public ModelAndView guardarVoto(@PathVariable("id") Long id, @Valid FaccionType ft, BindingResult br, Principal principal) throws VotoDuplicadoException{
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType faccion = partidaService.getFaccionesTypeByName(ft.getName());
        Partida p = partidaService.getPartidaById(id).get();
        Integer maxVoto = votoService.getVotos().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        try{
            votoService.CrearVoto(j,faccion,p,maxVoto);
        }catch(VotoDuplicadoException v){
            ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
            return res;
        }
        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        return res;
    }

    @GetMapping("/jugar/pretor/{id}")
    public ModelAndView partidaPretor(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        response.addHeader("Refresh", "10");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Voto> votos = votoService.getVotosRondaTurno(p);
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        ModelAndView result = pasarPropiedadesComunes(PRETOR_JUGAR, principal, p, j);
        result.addObject("elegir", elegir);
        result.addObject("votos", votos);
        return result;
    }

    @GetMapping("/jugar/pretor/edit/{partidaId}/{votoId}")
    public ModelAndView pretorEditVoto(@PathVariable("partidaId") Long partidaId, @PathVariable("votoId") Long votoId,
                                HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(partidaId).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Voto v = votoService.getVotoById(votoId).get();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        ModelAndView res = pasarPropiedadesComunes(PRETOR_EDIT, principal, p, j);
        res.addObject("elegir", elegir);
        res.addObject("voto", v);
        return res;
    }

    @PostMapping("/jugar/pretor/edit/{partidaId}/{votoId}")
    public String partidaPretor(@PathVariable("partidaId") Long partidaId,@PathVariable("votoId") Long votoId,
                                    @Valid Voto voto,HttpServletResponse response, Principal principal) throws VotoDuplicadoException{
        response.addHeader("Refresh", "10");
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Partida p = partidaService.getPartidaById(partidaId).get();
        Voto votoToUpdate = votoService.getVotoById(votoId).get();
        votoToUpdate.setFaccion(voto.getFaccion());
        try{
            votoService.saveVoto(votoToUpdate,j);
        }catch(VotoDuplicadoException v){
            return "redirect:/partidas/jugar/{partidaId}";
        }
        List<Voto> votos = votoService.getVotosRondaTurno(p);
        participacionService.actualizaParticipacionesYPartida(votos, p);
        if(p.getTurno() !=1 &&p.getRonda()==1){
            p.setFase(1);
        }
        if(p.getTurno() == 1 && p.getRonda()==1){
            p.setTurno(p.getTurno()+1);
            jugadorService.reparteRoles(p);;
        }
        partidaService.save(p);
        return "redirect:/partidas/jugar/{partidaId}";
    }

    @GetMapping("/jugar/consul/{id}")
    public ModelAndView partidaConsul(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        response.addHeader("Refresh", "10");
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        FaccionType ft = new FaccionType();
        Integer maxid = partidaService.getFaccionesType().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        ft.setId(maxid+1);
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        ModelAndView result = pasarPropiedadesComunes(CONSUL_JUGAR, principal, p, j);
        result.addObject("faccionType", ft);
        result.addObject("opciones", opciones);
        return result;
    }

    @GetMapping("/jugar/consul/eleccionP/{id}")
    public ModelAndView escogerPretor(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Jugador> jugadores = p.getJugadores();
        List<Jugador> jugadoresFiltrado = jugadores.stream()
                    .filter(x-> x.getRol() != null).filter(x-> !x.getRol().getName().equals("Pretor"))
                    .filter(x-> !x.getRol().getName().equals("Consul")).filter(x-> x.getYaElegido() != true)
                    .collect(Collectors.toList());
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        ModelAndView res = pasarPropiedadesComunes(ESCOGER_PRETOR, principal, p, j);
        res.addObject("opciones", opciones);
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
        RolType sinRol = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
        RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
        jugadorService.reestableceRolesDeNoElegidos(p, consul, sinRol);
        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        return res;
    }

    @GetMapping("/jugar/consul/eleccionE/{id}")
    public ModelAndView escogerEdil(@PathVariable("id") Long id, HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(id).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        List<Jugador> jugadoresFiltrado = jugadorService.jugadoresQuePuedenSerEdil(p);
        List<String> opciones = j.getParticipacionEnPartida(p).getOpciones().stream().map(x->x.getName()).collect(Collectors.toList());
        ModelAndView res = pasarPropiedadesComunes(ESCOGER_EDIL, principal, p, j);
        res.addObject("opciones", opciones);
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
        RolType sinRol = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
        RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
        jugadorService.reestableceRolesDeNoElegidos(p, consul, sinRol);
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
        List<RolType> roles = jugadorService.getRoles();
        if(p.getRonda()!=2){
            jugadorService.reparteRoles(p);
        }
        jugadorService.cambiaRonda(p);
        if(p.getRonda() == 3){
            p.terminaPartida(roles);
        }
        jugadorService.consulRonda2(p, roles);
        ModelAndView res = new ModelAndView("redirect:/partidas/jugar/{id}");
        partidaService.save(p);
        participacionService.save(participacion);  
        return res;
    }

    @PostMapping("/new")
    public ModelAndView saveNewPartida(@Valid Partida partida, BindingResult br, Principal principal){
        if(br.hasErrors()){
            return new ModelAndView(PARTIDAS_CREAR,br.getModel());
        }
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        partidaService.CrearPartida(j, partida);
        ModelAndView result =new ModelAndView("redirect:/partidas/join/"+partida.getId());
        return result;
    }

    @GetMapping("/jugar/pretor/edit2/{partidaId}/{votoId}")
    public ModelAndView pretorEditVotoRonda2(@PathVariable("partidaId") Long partidaId, @PathVariable("votoId") Long votoId,
                                HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(partidaId).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Voto v = votoService.getVotoById(votoId).get();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        ModelAndView res = pasarPropiedadesComunes(PRETOR_EDIT2, principal, p, j);
        res.addObject("elegir", elegir);
        res.addObject("voto", v);
        return res;
    }

    @PostMapping("/jugar/pretor/edit2/{partidaId}/{votoId}")
    public String partidaPretorEditaVoto(@PathVariable("partidaId") Long partidaId,@PathVariable("votoId") Long votoId,
                                    @Valid Voto voto,HttpServletResponse response, Principal principal) throws VotoDuplicadoException{
        response.addHeader("Refresh", "10");
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Partida p = partidaService.getPartidaById(partidaId).get();
        Voto votoToUpdate = votoService.getVotoById(votoId).get();
        votoToUpdate.setElegido(voto.getElegido());
        if(votoToUpdate.getFaccion().getName().equals("Mercader")){ //Da igual lo que diga el pretor, si el voto que observa es de mercader, todos deben saberlo y quien voto está obligado a cambiarlo
            votoToUpdate.setElegido(true);
        }
        try{
            votoService.saveVoto(votoToUpdate,j);
        }catch(VotoDuplicadoException v){
            return "redirect:/partidas/jugar/{partidaId}";
        }
        List<RolType> roles = jugadorService.getRoles();
        List<Voto> votos = votoService.getVotosRondaTurno(p);
        if(!votoToUpdate.getElegido()){ // Si no se elige el voto, se tiene que actualizar el marcador
            actualizaMarcadorYDatosRonda2(p, votos, roles);
        }
        if(votoToUpdate.getElegido()){
            p.setFase(1);
        }
        else if(p.getRonda() == 2 && p.getTurno() == 1){
            p.setFase(2);
        }
        partidaService.save(p);
        return "redirect:/partidas/jugar/{partidaId}";
    }

    public void actualizaMarcadorYDatosRonda2(Partida p,List<Voto> votos,List<RolType> roles){
        participacionService.actualizaParticipacionesYPartida(votos, p);
            if(p.getTurno()!=1 && p.getRonda()==2){
                p.setTurno(p.getTurno()+1);
                jugadorService.consulRonda2(p, roles);
                if(p.getTurno()>p.getNumJugadores()){
                    p.setRonda(p.getRonda()+1);
                    p.setTurno(1);
                }
            }
            partidaService.comprobarSiSobrepasaLimite(p);
            if(p.getRonda() == 3){
                p.terminaPartida(roles);
            }
            for(int i = 0;i<p.getJugadores().size();i++){
                p.getJugadores().get(i).setYaElegido(false);
            }
    }
    
    @GetMapping("/jugar/edil/edit/{partidaId}/{votoId}")
    public ModelAndView edilEditVoto(@PathVariable("partidaId") Long partidaId, @PathVariable("votoId") Long votoId,
                                HttpServletResponse response, Principal principal){
        Partida p = partidaService.getPartidaById(partidaId).get();
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        ModelAndView res = pasarPropiedadesComunes(EDIL_EDIT, principal, p, j);
        Voto v = votoService.getVotoById(votoId).get();
        List<FaccionType> elegir = j.getParticipacionEnPartida(p).getOpciones();
        List<FaccionType> facciones = partidaService.getFaccionesType().stream()
                                                    .filter(x->!x.getName().equals(v.getFaccion().getName()))
                                                    .filter(x->!x.getName().equals("No decidido"))
                                                    .collect(Collectors.toList());
        res.addObject("elegir", elegir);
        res.addObject("voto", v);
        res.addObject("facciones", facciones);
        return res;
    }

    @PostMapping("/jugar/edil/edit/{partidaId}/{votoId}")
    public String partidaEditEditaVoto(@PathVariable("partidaId") Long partidaId,@PathVariable("votoId") Long votoId,
                                    @Valid Voto voto,HttpServletResponse response, Principal principal) throws VotoDuplicadoException{
        response.addHeader("Refresh", "10");
        Jugador j = jugadorService.getJugadorByUsername(principal.getName());
        Partida p = partidaService.getPartidaById(partidaId).get();
        Voto votoToUpdate = votoService.getVotoById(votoId).get();
        votoToUpdate.setFaccion(voto.getFaccion());
        try{
            votoService.saveVoto(votoToUpdate,j);
        }catch(VotoDuplicadoException v){
            return "redirect:/partidas/jugar/{partidaId}";
        }
        List<Voto> votos = votoService.getVotosRondaTurno(p);
        participacionService.actualizaParticipacionesYPartida(votos, p);
        if(p.getRonda()==2 && p.getTurno()==1){
            p.setFase(2);
        }else{
            p.setTurno(p.getTurno()+1);
            p.setFase(0);
        }
        List<RolType> roles = jugadorService.getRoles();
        jugadorService.consulRonda2(p, roles);
        for(int i = 0;i<p.getJugadores().size();i++){
            p.getJugadores().get(i).setYaElegido(false);
        }
        if(p.getTurno()>p.getNumJugadores()){
            p.setRonda(p.getRonda()+1);
            p.setTurno(1);
        }
        partidaService.comprobarSiSobrepasaLimite(p);
        if(p.getRonda() == 3){
            p.terminaPartida(roles);
        }        
        partidaService.save(p);
        return "redirect:/partidas/jugar/{partidaId}";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePartida(@PathVariable("id") long id){
        partidaService.deletePartida(id);
        return new ModelAndView("redirect:/partidas");
    }

    public ModelAndView pasarPropiedadesComunes(String url, Principal principal, Partida p, Jugador j){
        ModelAndView res = new ModelAndView(url);
        FaccionType faccionApoyada = j.getParticipacionEnPartida(p).getFaccionApoyada();
        res.addObject("principal", principal);
        res.addObject("jugadorLog", j);
        res.addObject("partida", p);
        res.addObject("faccionApoyada", faccionApoyada);
        return res;
    }
}