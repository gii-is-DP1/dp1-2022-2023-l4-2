package org.springframework.samples.petclinic.estadistica;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logros")
public class LogroController {
    public static final String LOGROS_LISTING = "logros/logrosList";
    private final String LOGROS_FORM="/achievements/createOrUpdateLogroForm";

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

    @GetMapping("/{id}/edit")
    public ModelAndView editLogro(@PathVariable int id){
        Logro logro = logroService.getLogroById(id).get();        
        ModelAndView result=new ModelAndView(LOGROS_FORM);
        result.addObject("achievement", logro);
        return result;
    }


    @PostMapping("/{id}/edit")
    public ModelAndView saveLogro(@PathVariable int id,Logro logro){

        Logro logroToBeUpdated=logroService.getLogroById(id).get();
        BeanUtils.copyProperties(logro,logroToBeUpdated,"id");
        logroService.save(logroToBeUpdated);
        return showLogros();
    }
    
}
