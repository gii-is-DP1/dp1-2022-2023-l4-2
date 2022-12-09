package org.springframework.samples.petclinic.estadistica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticaGeneralController {
    
    public static final String ESTADISTICAS_PAGE = "estadisticas/estadisticasPage";
    private EstadisticaGeneralService eGeneralService;

    @Autowired
    public EstadisticaGeneralController (EstadisticaGeneralService eGeneralService){
        this.eGeneralService = eGeneralService;
    }


    @GetMapping()
    public ModelAndView getEstadisticas(){
        List<Partida> partidas = eGeneralService.getPartidasNoActivas();
        ModelAndView result = new ModelAndView(ESTADISTICAS_PAGE);
		result.addObject("numPartidas", eGeneralService.getNumPartidas(partidas));

        result.addObject("victoriasLeal", eGeneralService.getVictoriasLeal(partidas));
        result.addObject("victoriasTraidor", eGeneralService.getVictoriasTraidor(partidas));
        result.addObject("victoriasMercader", eGeneralService.getVictoriasMercader(partidas));
        result.addObject("mediaMinutosPartida", eGeneralService.getMediaMinutosPartida(partidas));
        result.addObject("maxVotosAFavorCesar", eGeneralService.getMaxVotosAFavorCesar(partidas));
        result.addObject("maxVotosEnContraCesar", eGeneralService.getMaxVotosEnContraCesar(partidas));
        result.addObject("maxDiferenciaDeVotos", eGeneralService.getMaxDifVotos(partidas));
        result.addObject("faccionPerdedora", eGeneralService.getFaccionPerdedora(partidas));
        result.addObject("topJugadoresConVictoria", eGeneralService.getTopJugadoresConVictorias());
        result.addObject("topJugadoresConPartida", eGeneralService.getTopJugadoresConPartidas());
		return result;

    }
}
