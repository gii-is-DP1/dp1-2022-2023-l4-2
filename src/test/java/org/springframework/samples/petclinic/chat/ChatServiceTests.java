package org.springframework.samples.petclinic.chat;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ChatServiceTests {


    @Autowired
    protected ChatService chatService;

    @Test
    void shouldGetChats(){
        List<Chat> chats = chatService.getAll();

        assertNotNull(chats);
        assertFalse(chats.isEmpty());
    }

    @Test
    void shouldFindById(){
        List<Chat> chats = chatService.getAll();
        Chat chat = chats.get(0);
        Chat find = chatService.getByPartidaId(8L);
        assertEquals(chat, find);
    }

    @Test
    void negativeFindById(){
        Chat c = chatService.getByPartidaId(-1L);
        List<Chat> chats = chatService.getAll();
        Boolean res = false;
        for(Chat i : chats){
            if(i.equals(c)){
                res = true;
                break;
            }
        }
        assertFalse(res);
    }

    @Test
    void shouldSaveChat(){
        List<Chat> chats = chatService.getAll();
        Chat chat = chats.get(0);
        assertEquals(chat.getId(), (Integer)8);
        chat.setId(2);
        chatService.save(chat);
        assertEquals(chat.getId(), (Integer) 2);
    }

    @Test
    void negativeSaveChat(){
        Chat c = null;
        assertThrows(InvalidDataAccessApiUsageException.class, () -> chatService.save(c)); 
    }



    
}
