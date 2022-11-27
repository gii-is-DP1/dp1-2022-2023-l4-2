package org.springframework.samples.petclinic.mensaje;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
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

    public List<Mensaje> getAllByPartidaId(Partida p){
        return mensajeRepo.findByPartidaId(p.getId());
    }

    @Transactional(readOnly = true)
    public List<Mensaje> getAll(){
        List<Mensaje> m = mensajeRepo.findAll();
        return m;
    }

    public void saveMensaje(Mensaje m) throws DataAccessException{
        mensajeRepo.save(m);
    }

}