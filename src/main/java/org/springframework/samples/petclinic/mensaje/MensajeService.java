package org.springframework.samples.petclinic.mensaje;

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
        return mensajeRepo.findAll();
    }

    public void saveMensaje(Mensaje m) throws DataAccessException{
        mensajeRepo.save(m);
    }

}