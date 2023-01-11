package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.FaccionType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

	@Autowired
	JugadorService jugadorService;
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

	    return "welcome";
	  }
	  

	  @GetMapping({"/home"})
	  public ModelAndView home(Map<String, Object> model, Principal principal) {	    
		ModelAndView mv = new ModelAndView("home");

		if (principal != null) {
			mv.addObject("userName", principal.getName());
			Jugador j = jugadorService.getJugadorByUsername(principal.getName());
			String faccionFav = "Mercader";
			if(j != null && j.getFaccionFavorita() != null) {
				faccionFav = j.getFaccionFavorita();
			}

			if (faccionFav == ""){
				faccionFav = "Mercader";
			}
			mv.addObject("faccion", faccionFav);
		} 
		
	    return mv;
	  }

	  @GetMapping({"/instrucciones"})
	  public String instrucciones(Map<String, Object> model) {	    

	    return "instrucciones";
	  }

	  @GetMapping({"/diaHistorico"})
	  public String diaHistorico(Map<String, Object> model) {	    

	    return "diaHistorico";
	  }


}
