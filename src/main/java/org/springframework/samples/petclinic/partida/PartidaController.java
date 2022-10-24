package org.springframework.samples.petclinic.partida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    public static final String PARTIDAS_LISTING = "partidas/partidasList";
    private PartidaService partidaService;

    @Autowired
    public PartidaController(PartidaService partidaService){
        this.partidaService = partidaService;
    }

    @GetMapping()
	public ModelAndView showPartidas() {
		ModelAndView result = new ModelAndView(PARTIDAS_LISTING);
		result.addObject("partidas", partidaService.getPartidas());
		return result;
	}

    @GetMapping("/delete/{id}")
    public ModelAndView deletePartida(@PathVariable("id") long id){
        partidaService.deletePartida(id);
        return new ModelAndView("redirect:/partidas");
    }

}
