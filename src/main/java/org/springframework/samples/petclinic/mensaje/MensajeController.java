package org.springframework.samples.petclinic.mensaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mensaje")
public class MensajeController {
    
    @Autowired
    MensajeService mensajeService;

    @Autowired
    public MensajeController(MensajeService repo){
        this.mensajeService = repo;
    }
}