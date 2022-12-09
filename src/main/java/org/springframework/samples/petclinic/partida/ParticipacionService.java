package org.springframework.samples.petclinic.partida;

import java.util.Collections;
import java.util.Comparator;
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
    
    public void creaParticipacion(Jugador j, Partida p){
        Participacion part = new Participacion();
        Integer idp = getParticipaciones().stream().map(x->x.getId()).max(Comparator.comparing(x->x)).orElse(1);
        part.setId(idp+1);
        if(p.getParticipaciones().isEmpty()){
            part.setNumConsul(0);
        }else{
            part.setNumConsul(0);
        }
        if(p.getAnfitrion() == j.getUser().getUsername()){
        part.setEsAnfitrion(true);
        }else{
            part.setEsAnfitrion(false);
        }
        part.setVotosContraCesar(0);
        part.setVotosFavorCesar(0);
        part.setVotosNeutros(0);
        part.setPartidas(p);
        part.setFaccionApoyada(null);
        part.setOpciones(List.of());
        List<Participacion> ls2 = p.getParticipaciones();
        List<Participacion> ls = j.getParticipaciones();
        save(part);
        ls.add(part);
        ls2.add(part);
        j.setEstaEnPartida(true);
        j.setParticipaciones(ls);
        p.setParticipaciones(ls2);
    }
}
