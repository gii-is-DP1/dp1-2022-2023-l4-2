package org.springframework.samples.petclinic.chat;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeService {

    @Autowired
    MensajeRepository mensajeRepo;

    @Autowired
    public MensajeService(MensajeRepository repo){
        this.mensajeRepo = repo;
    }
    
    @Transactional(readOnly = true)
    public List<Mensaje> getAllByJugadorId(Jugador j){
        return mensajeRepo.findByJugadorId(j.getId());
    }

    @Transactional(readOnly = true)
    public List<Mensaje> getAll(){
        List<Mensaje> m = mensajeRepo.findAll();
        return m;
    }

    @Transactional
    public void saveMensaje(Mensaje m) throws DataAccessException{
        mensajeRepo.save(m);
    }

    public Integer devuelveIdMax(){
        Integer idMax;
        List<Mensaje> todos = getAll();
        if(todos.isEmpty()){
            idMax=0;
        }else{
            idMax= todos.stream().max(Comparator.comparing(x -> x.getId())).map(x -> x.getId()).get();
        }
        return idMax;
    }

}