package org.springframework.samples.petclinic.estadistica;

import java.util.List;

import org.eclipse.jdt.internal.compiler.flow.FlowContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.FaccionType;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticaGeneralController {
    
    public static final String ESTADISTICAS_PAGE = "estadisticas/estadisticasPage";
    private PartidaService partidaService;

    @Autowired
    public EstadisticaGeneralController (PartidaService partidaService){
        this.partidaService = partidaService;
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
		return result;
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
            tiempoPartidas += partida.getTiempo();
        }
        result = tiempoPartidas/partidas.size();
        return result;
    }

    private Long getVictoriasLeal(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if (partida.getFaccionGanadora().getName().equals("Leal")){
                result++;
            }
        }
        return result;
    }

    private Long getVictoriasTraidor(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if (partida.getFaccionGanadora().getName().equals("Traidor")){
                result++;
            }
        }
        return result;
    }

    private Long getVictoriasMercader(List<Partida> partidas) {
        Long result = 0L;
        for(Partida partida: partidas){
            if (partida.getFaccionGanadora().getName().equals("Mercader")){
                result++;
            }
        }
        return result;
    }

    private static Long getNumPartidas(List<Partida> partidas){
        return Long.valueOf(partidas.size());
    }

}
