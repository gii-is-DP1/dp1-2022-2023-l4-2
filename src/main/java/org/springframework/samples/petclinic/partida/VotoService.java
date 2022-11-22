package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    
    
}
