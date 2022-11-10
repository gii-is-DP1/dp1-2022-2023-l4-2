package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

	    return "welcome";
	  }
	  

	  @GetMapping({"/home"})
	  public String home(Map<String, Object> model) {	    

	    return "home";
	  }

	  @GetMapping({"/instrucciones"})
	  public String instrucciones(Map<String, Object> model) {	    

	    return "instrucciones";
	  }
}
