package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotoService {

    private VotoRepository votoRepository;
    
    @Autowired
    public VotoService(VotoRepository votoRepository){
        this.votoRepository = votoRepository;
    }

    @Transactional(readOnly = true)
    public List<Voto> getVotos(){
        return votoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Voto> getVotosRondaTurno(Partida p){
        return votoRepository.findVotosRondaTurno(p.getRonda(), p.getTurno(),p);
    }

    @Transactional
    public void saveVoto(Voto v) throws DataAccessException{
        votoRepository.save(v);
    }
    
    @Transactional(readOnly = true)
    public List<Voto> getVotosTurnoJugador(Partida p, Jugador j) throws Exception{
        if (p.getJugadores().contains(j)){
            return votoRepository.findVotosTurnoJugador(j, p.getTurno(), p.getRonda(), p);
        } else {
            throw new Exception("El jugador " + j.getUser().getUsername() + " no esta en la partida");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Voto> getVotoById(Long votoId) {
        return votoRepository.findById(votoId.intValue());
    }
    
    @Transactional(readOnly = true)
    public List<Voto> getVotosElegidosRondaTurno(Partida p, Jugador j){
        return votoRepository.findVotosTurnoJugador(j, p.getTurno(), p.getRonda(),p)
                            .stream()
                            .filter(x->x.getElegido()!=null)
                            .filter(x->x.getElegido())
                            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Voto getVotoMercaderElegidoRondaTurno(Partida p){
        Voto v = null;
        v = votoRepository.findVotosRondaTurno(p.getRonda(), p.getTurno(),p).stream()
                    .filter(x->x.getFaccion().getName().equals("Mercader"))
                    .filter(x->x.getElegido()!=null && x.getElegido())
                    .findFirst().orElse(null);
        return v;
    }
}
