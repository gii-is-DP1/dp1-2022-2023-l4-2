package org.springframework.samples.petclinic.estadistica;

import java.util.Optional;

import javax.validation.Valid;


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
    private final String LOGROS_FORM = "/logros/updateLogroForm";
    private final String LOGROS_FORM2= "/logros/createLogroForm";

    private LogroService logroService;

    @Autowired
    public LogroController(LogroService logroService) {
        this.logroService = logroService;
    }

    @GetMapping()
    public ModelAndView showLogros() {
        ModelAndView result = new ModelAndView(LOGROS_LISTING);
        result.addObject("logros", logroService.getLogros());
        return result;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteLogro(@PathVariable("id") long id) {
        logroService.deleteLogro(id);
        return new ModelAndView("redirect:/logros");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editLogro(@PathVariable("id") long id) {
        ModelAndView result = new ModelAndView(LOGROS_FORM);
        Optional<Logro> logro = logroService.getLogroById(id);
        if (logro.isPresent()) {
            result.addObject("logro", logro.get());

        } else {
            result = showLogros();
            result.addObject("message", "Room with id " + id + " not found");
        }
        return result;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView saveLogro(Logro logro, BindingResult br, @PathVariable("id") long id) {
        if (!br.hasErrors()) {
            logroService.save(logro);
        } else {

        }
        return new ModelAndView("redirect:/logros");
    }
    @GetMapping("/new")
    public ModelAndView createLogro(){
        Logro logro = new Logro();
        ModelAndView result = new ModelAndView(LOGROS_FORM2);
        result.addObject("logro", logro);
        return result;
    }

    @PostMapping("/new")
    public ModelAndView saveNewLogro(@Valid Logro logro, BindingResult br){
        if(br.hasErrors()){
            return new ModelAndView(LOGROS_FORM2,br.getModel());
        }
        logroService.save(logro);
        ModelAndView result =new ModelAndView("redirect:/logros");
        result.addObject("message", "El logro se añadió correctamente");
        return result;
    }

    
            

}

