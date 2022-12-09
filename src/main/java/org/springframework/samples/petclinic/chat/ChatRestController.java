package org.springframework.samples.petclinic.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ChatRestController {

    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("/chat")
    public List<Chat> getAllChats(){
        return chatRepository.findAll();
    }

    @GetMapping("/chat/{id}")
    public Chat getChatByPartidaId(@PathVariable("id")Long id){
        return chatRepository.findByPartidaId(id);
    }
    
}
