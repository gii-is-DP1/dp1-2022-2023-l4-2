package org.springframework.samples.petclinic.estadistica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logros")
public class LogroController {
    public static final String LOGROS_LISTING = "logros/logrosList";
    private LogroService logroService;

    @Autowired
    public LogroController(LogroService logroService){
        this.logroService = logroService;
    }

    @GetMapping()
	public ModelAndView showLogros() {
		ModelAndView result = new ModelAndView(LOGROS_LISTING);
		result.addObject("logros", logroService.getLogros());
		return result;
	}

    @GetMapping("/delete/{id}")
    public ModelAndView deleteLogro(@PathVariable("id") long id){
        logroService.deleteLogro(id);
        return new ModelAndView("redirect:/logros");
    }
    
}
