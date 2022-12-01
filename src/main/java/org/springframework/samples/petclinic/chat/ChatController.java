package org.springframework.samples.petclinic.chat;

import java.net.PortUnreachableException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
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
    
    MensajeService mensajeService;

    @Autowired
    PartidaService partidaService;

    @Autowired
    JugadorService jugadorService;

    @Autowired
    ChatService chatService;


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

    @GetMapping("/creaChat/{id}")
    public ModelAndView creaChat(@PathVariable("id") Long id){

        if(chatService.getByPartidaId(id)==null){
            Chat c = new Chat();
            Partida partida = partidaService.getPartidaById(id).get();
            c.setPartida(partida);
            //Integer i = (int) (long) id;
            //c.setId(i);
            chatService.save(c);
        }
        ModelAndView result =new ModelAndView("redirect:/chat/{id}");
        return result;
    }

    @GetMapping("/{id}")
    public ModelAndView chatDePartida(@PathVariable("id") Long id, Principal principal){
        Partida pActual = partidaService.getPartidaById(id).get();
        Chat c = chatService.getByPartidaId(id);
        Jugador jActual = jugadorService.getJugadorByUsername(principal.getName());
        ModelAndView result = new ModelAndView(SHOW_CHAT);
        result.addObject("jActual", jActual);
        result.addObject("pActual", pActual);
        result.addObject("chat", c);
        result.addObject("mensaje", new Mensaje());
        
        return result;
    }

    @PostMapping("/{id}")
    public ModelAndView nuevoMensaje(@PathVariable("id") Long id, @Valid Mensaje mensaje, BindingResult br, Principal principal){

        if(br.hasErrors()){
            return new ModelAndView(SHOW_CHAT,br.getModel());
        }
        Jugador jActual = jugadorService.getJugadorByUsername(principal.getName());
        List<Mensaje> todos = mensajeService.getAll();
        Integer idMax;
        if(todos.isEmpty()){
            idMax=0;
        }else{
            idMax= todos.stream().max(Comparator.comparing(x -> x.getId())).map(x -> x.getId()).get();
        }
        Chat c = chatService.getByPartidaId(id);
        mensaje.setId(idMax + 1);
        mensaje.setJugador(jActual);
        mensaje.setChat(c);
        List<Mensaje> m = c.getMensajes();
        m.add(mensaje);
        c.setMensajes(m);
        chatService.edit(c);
        ModelAndView result = new ModelAndView("redirect:/chat/{id}");
        return result;
    }

}