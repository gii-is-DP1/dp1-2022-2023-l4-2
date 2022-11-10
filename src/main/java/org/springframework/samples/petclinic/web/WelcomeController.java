package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

	    return "welcome";
	  }
	  

	  @GetMapping({"/home"})
	  public ModelAndView home(Map<String, Object> model, Principal principal) {	    
		ModelAndView mv = new ModelAndView("home");
		if (principal != null){
			mv.addObject("userName", principal.getName());
		}
	    return mv;
	  }

	  @GetMapping({"/instrucciones"})
	  public String instrucciones(Map<String, Object> model) {	    

	    return "instrucciones";
	  }


}
