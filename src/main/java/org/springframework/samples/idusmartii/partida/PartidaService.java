package org.springframework.samples.idusmartii.partida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaService {
    private PartidaRepository partidaRepo;

    @Autowired
    public PartidaService(PartidaRepository partidaRepo){
        this.partidaRepo = partidaRepo;
    }

    @Transactional(readOnly = true)
    public List<Partida> getPartidas(){
        return partidaRepo.findAll();
    }
    @Transactional
    public void deletePartida(long id) {
        partidaRepo.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Optional<Partida> getPartidaById(long id) {
		Optional<Partida> result = partidaRepo.findById(id);
		return result;
	}

  
}
