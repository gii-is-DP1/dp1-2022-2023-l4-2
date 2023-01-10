package org.springframework.samples.petclinic.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepo;

    @Autowired
    public ChatService(ChatRepository repo){
        this.chatRepo = repo;
    }
    
    @Transactional(readOnly = true)
    public Chat getByPartidaId(Long id){
        return chatRepo.findByPartidaId(id);
    }

    @Transactional(readOnly = true)
    public List<Chat> getAll(){
        List<Chat> c = chatRepo.findAll();
        return c;
    }

    @Transactional
    public void save(Chat c) throws DataAccessException{
        chatRepo.save(c);
    }

    @Transactional
    public void edit(Chat c) throws DataAccessException{
        Chat toUpdate = chatRepo.findById(c.getId()).get();
        toUpdate.setId(c.getId());
        toUpdate.setMensajes(c.getMensajes());
        toUpdate.setPartida(c.getPartida());
        chatRepo.save(toUpdate);
    }

    
}
