package org.springframework.samples.petclinic.partida;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipacionService {
    
    private ParticipacionRepository particRepository;

    @Autowired
    public ParticipacionService(ParticipacionRepository participacionRepository){
        this.particRepository = participacionRepository;
    }

    @Transactional(readOnly = true)
    public List<Participacion> getParticipaciones(){
        return particRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Participacion> getParticipacionById(Integer id) {
        Optional<Participacion> result = particRepository.findById(id);
        return result;
    }
    
    @Transactional
    public void deleteParticipacion(Integer id) {
        particRepository.deleteById(id);
    }

    @Transactional
    public void save(Participacion p) {
        particRepository.save(p);
    }

    public void ordenaNumConsul(Partida p){
        List<Jugador> jugadores = p.getJugadores();
        Collections.shuffle(jugadores);
        for(int i = 0; i<jugadores.size(); i++){
            Jugador j = jugadores.get(i);
            Participacion part = j.getParticipacionEnPartida(p);
            part.setNumConsul(i+1);
            save(part);
        }

    }
}
