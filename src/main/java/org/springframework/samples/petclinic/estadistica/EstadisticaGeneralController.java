package org.springframework.samples.petclinic.estadistica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.flow.FlowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.FaccionType;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.build.HashCodeAndEqualsPlugin.Sorted;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticaGeneralController {
    
    public static final String ESTADISTICAS_PAGE = "estadisticas/estadisticasPage";
    private PartidaService partidaService;
    private JugadorService jugadorService;

    @Autowired
    public EstadisticaGeneralController (PartidaService partidaService, JugadorService jugadorService){
        this.partidaService = partidaService;
        this.jugadorService = jugadorService;
    }


    @GetMapping()
    public ModelAndView getEstadisticas(){
        List<Partida> partidas = partidaService.getPartidas();
        ModelAndView result = new ModelAndView(ESTADISTICAS_PAGE);
		result.addObject("numPartidas", getNumPartidas(partidas));
        result.addObject("victoriasLeal", getVictoriasLeal(partidas));
        result.addObject("victoriasTraidor", getVictoriasTraidor(partidas));
        result.addObject("victoriasMercader", getVictoriasMercader(partidas));
        result.addObject("mediaMinutosPartida", getMediaMinutosPartida(partidas));
        result.addObject("maxVotosAFavorCesar", getMaxVotosAFavorCesar(partidas));
        result.addObject("maxVotosEnContraCesar", getMaxVotosEnContraCesar(partidas));
        result.addObject("maxDiferenciaDeVotos", getMaxDifVotos(partidas));
        result.addObject("faccionPerdedora", getFaccionPerdedora(partidas));
        result.addObject("topJugadoresConVictoria", getTopJugadoresConVictorias(partidas));
		return result;
    }

    private Map<String, Integer> getTopJugadoresConVictorias(List<Partida> partidas) {
        List<Jugador> jugadores = jugadorService.getJugadores();
        jugadores.stream().sorted(Comparator.comparing(x -> x.getPartidasGanadas()));
        Map<String, Integer> res = new HashMap<String,Integer>();
        for(int i = 0; i<jugadores.size() && i<5 ; i++){
            Jugador j = jugadores.get(i);
            Integer ganadas = j.getPartidasGanadas();
            res.put(j.getUser().getUsername(), ganadas);
        }
        return res;
    }
   
    private Long getMaxVotosEnContraCesar(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getVotosContraCesar() > result){
                result = partida.getVotosContraCesar();
            }
        }
        return result;
    }

    private Long getMaxVotosAFavorCesar(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getVotosFavorCesar() > result){
                result = partida.getVotosFavorCesar();
            }
        }
        return result;
    }

    private Float getMediaMinutosPartida(List<Partida> partidas) {
        Float result = 0f;
        Float tiempoPartidas = 0f;
        for(Partida partida: partidas){
            if(partida.getTiempo() > 0){
                tiempoPartidas += partida.getTiempo();
            }
        }
        result = Float.valueOf(Math.round(tiempoPartidas/partidas.size()));
        return result;
    }

    private Long getVictoriasLeal(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
            if (partida.getFaccionGanadora().getName().equals("Leal")){
                result++;
            }
        }
        }
        return result;
    }

    private Long getVictoriasTraidor(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
            if (partida.getFaccionGanadora().getName().equals("Traidor")){
                result++;
            }
        }
        }
        return result;
    }

    private Long getVictoriasMercader(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
            if (partida.getFaccionGanadora().getName().equals("Mercader")){
                result++;
            }
        }
        }
        return result;
    }

    private String getFaccionPerdedora(List<Partida> partidas) {
        String res = "Hay facciones empatadas en numero de victorias";
        Map<FaccionType,Integer> aux = new HashMap<FaccionType, Integer>();
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
            if (aux.containsKey(partida.getFaccionGanadora())){
                aux.put(partida.getFaccionGanadora(), aux.get(partida.getFaccionGanadora()) + 1);
            }else{
                aux.put(partida.getFaccionGanadora(),  1);
            }
        }
        }
        String res1 = aux.entrySet().stream().min(Comparator.comparing(x->x.getValue())).get().getKey().getName();
        if(res1 != null){
            return res1;
        }else{
            return res;
        }
    }

    private Long getMaxDifVotos(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getVotosFavorCesar()>0 || partida.getVotosContraCesar()>0){
            Long res = Math.abs(partida.getVotosFavorCesar() - partida.getVotosContraCesar());
            if( res > result){
                result = res;
            }
        }
        }
        return result;
    }

    private static Long getNumPartidas(List<Partida> partidas){
        return Long.valueOf(partidas.size());
    }

}
