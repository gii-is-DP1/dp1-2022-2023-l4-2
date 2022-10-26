package org.springframework.samples.petclinic.estadistica;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logros")
public class LogroController {
    public static final String LOGROS_LISTING = "logros/logrosList";
    private final String LOGROS_FORM="/logros/createOrUpdateLogroForm";

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

    @GetMapping("/edit/{id}")
    public ModelAndView editLogro(@PathVariable("id") long id){
        ModelAndView result = new ModelAndView(LOGROS_FORM);
        Optional<Logro> logro = logroService.getLogroById(id);
        if(logro.isPresent()){
            result.addObject("logro", logro.get());
        }else{
            result=showLogros();
            result.addObject("message", "Room with id "+ id + " not foudn");
        }
        return result;
    }


    @PostMapping("/edit/{id}")
    public ModelAndView saveLogro(Logro logro, BindingResult br,@PathVariable("id") long id){
        
//        Logro logroToBeUpdated=logroService.getLogroById(id).get();
//        BeanUtils.copyProperties(logro,logroToBeUpdated,"id");
//        logroService.save(logroToBeUpdated);
//        return showLogros();
        
        if(br.hasErrors()){
            logroService.save(logro);
        }else{

        }
        return showLogros();
    }
    
}
