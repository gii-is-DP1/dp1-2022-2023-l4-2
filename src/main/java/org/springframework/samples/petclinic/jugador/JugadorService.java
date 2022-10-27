package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {
    private JugadorRepository jugadorRepo;
    
    @Autowired
    public JugadorService(JugadorRepository jugadorRepo){
        this.jugadorRepo = jugadorRepo;
    }

    @Transactional
    public List<Jugador> getJugadores(){
        return jugadorRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Jugador> getJugadorById(long id){
        return jugadorRepo.findById(id);
    }
    
    @Transactional
    public void deleteJugador(long id){
        jugadorRepo.deleteById(id);
    }

    @Transactional
    public void save(Jugador j){
        jugadorRepo.save(j);
    }
}