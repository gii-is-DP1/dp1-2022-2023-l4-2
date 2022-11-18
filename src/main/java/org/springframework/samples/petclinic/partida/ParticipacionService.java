package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Participacion> getParticipacionById(long id) {
        Optional<Participacion> result = particRepository.findById(id);
        return result;
    }
    
    @Transactional
    public void deleteParticipacion(long id) {
        particRepository.deleteById(id);
    }

    @Transactional
    public void save(Participacion p) {
        particRepository.save(p);
    }
}
