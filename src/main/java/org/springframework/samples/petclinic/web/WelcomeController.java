package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Map;

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
	
	
	  @GetMapping({"/welcome"})
	  public String welcome(Map<String, Object> model) {	    

	    return "welcome";
	  }
	  

	  @GetMapping({"/","/home"})
	  public ModelAndView home(Map<String, Object> model, Principal principal) {	    
		ModelAndView mv = new ModelAndView("home");
		Jugador j = jugadorService.getJugadorByUsername(principal.getName());
		String faccionFav = j.getFaccionFavorita();
		String url = "/resources/images/SoldadoNeutral.png";

		if(faccionFav == "Leal"){
			url = "/resources/images/SoldadoLeal.png";
		}else if(faccionFav == "Traidor"){
			url = "/resources/images/SoldadoTraidor.jpg";
		}else{}

		if (principal != null){
			mv.addObject("userName", principal.getName());
		}
		mv.addObject("url", url);
	    return mv;
	  }

	  @GetMapping({"/instrucciones"})
	  public String instrucciones(Map<String, Object> model) {	    

	    return "instrucciones";
	  }


}
