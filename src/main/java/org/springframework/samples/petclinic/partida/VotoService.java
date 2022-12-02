package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

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
        return votoRepository.findVotosRondaTurno(p.getRonda(), p.getTurno());
    }

    @Transactional
    public void saveVoto(Voto v) throws DataAccessException{
        votoRepository.save(v);
    }
    
    @Transactional(readOnly = true)
    public List<Voto> getVotosTurnoJugador(Partida p, Jugador j) throws Exception{
        if (p.getJugadores().contains(j)){
            return votoRepository.findVotosTurnoJugador(j, p.getTurno(), p.getRonda());
        } else {
            throw new Exception("El jugador " + j.getUser().getUsername() + " no esta en la partida");
        }
        
    }

    @Transactional(readOnly = true)
    public Optional<Voto> getVotoById(Long votoId) {
        return votoRepository.findById(votoId.intValue());
    }
    
}
