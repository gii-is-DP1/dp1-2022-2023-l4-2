package org.springframework.samples.petclinic.mensaje;

import java.net.PortUnreachableException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {

    public static final String SHOW_CHAT = "partidas/chat";
    
    @Autowired
    MensajeService mensajeService;

    @Autowired
    PartidaService partidaService;

    @Autowired
    JugadorService jugadorService;


    @Autowired
    public ChatController(MensajeService repo){
        this.mensajeService = repo;
    }


    /*@GetMapping("/jugador/{id}")
    public ModelAndView chatDeJugador(@PathVariable("id") Integer id, Principal principal){
        List<Mensaje> mensajes = mensajeService.getAllByPartidaId(partidaService.getPartidaById(id).get());
        List<String> chat = mensajes.stream().map(m -> m.getContenido()).collect(Collectors.toList());
        ModelAndView result = new ModelAndView(SHOW_CHAT);
        result.addObject("chat", chat);
        return result;
    }*/

    @GetMapping("/partida/{id}")
    public ModelAndView chatDePartida(@PathVariable("id") Long id, Principal principal){
        Partida pActual = partidaService.getPartidaById(id).get();
        Collection<Mensaje> chat = mensajeService.getAllByPartidaId(pActual);
        Jugador jActual = jugadorService.getJugadorByUsername(principal.getName());
        ModelAndView result = new ModelAndView(SHOW_CHAT);
        result.addObject("jActual", jActual);
        result.addObject("pActual", pActual);
        result.addObject("chat", chat);
        result.addObject("chat", chat);
        result.addObject("mensaje", new Mensaje());
        
        return result;
    }

    @PostMapping("/partida/{id}")
    public ModelAndView nuevoMensaje(@PathVariable("id") Long id, @Valid Mensaje mensaje, BindingResult br, Principal principal){

        if(br.hasErrors()){
            return new ModelAndView(SHOW_CHAT,br.getModel());
        }
        Jugador jActual = jugadorService.getJugadorByUsername(principal.getName());
        Partida pActual = partidaService.getPartidaById(id).get();
        mensaje.setJugador(jActual);
        mensaje.setPartida(pActual);
        mensajeService.saveMensaje(mensaje);
        Collection<Mensaje> chat = mensajeService.getAllByPartidaId(pActual);
        ModelAndView result = new ModelAndView(SHOW_CHAT);
        result.addObject("pActual", pActual);
        result.addObject("jActual", jActual);
        result.addObject("chat", chat);
        
        return new ModelAndView("redirect:/chat/partida/{id}");
    }

    /* 
    @MessageMapping("/hello")
    @SendTo("/chat/joined")
    public MensajeBienvenida join(HelloMessage message) throws Exception {
        simulatedDelay();
        return new MensajeBienvenida("Hello " + message.getSender() + ", welcome to chat!");
    }
    @MessageMapping("/send/message")
    @SendTo("/chat/new/message")
    public Mensaje message(Mensaje mensaje) throws Exception {
        simulatedDelay();
        return new Mensaje(mensaje);
    }
    private void simulatedDelay() throws InterruptedException {
        Thread.sleep(3000);
    }
    */
}