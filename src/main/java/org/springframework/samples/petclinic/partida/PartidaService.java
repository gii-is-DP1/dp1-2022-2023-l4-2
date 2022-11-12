package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;
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

    @Transactional
    public void save(Partida p) {
        partidaRepo.save(p);
    }
  
    @Transactional
    public void edit(Partida p) {
        Partida toUpdate = partidaRepo.findById(p.getId()).get();
        toUpdate.setActiva(p.getActiva());
        toUpdate.setAnfitrion(p.getAnfitrion());
        toUpdate.setFaccionGanadora(p.getFaccionGanadora());
        toUpdate.setId(p.getId());
        toUpdate.setJugadores(p.getJugadores());
        toUpdate.setLimite(p.getLimite());
        toUpdate.setNumJugadores(p.getNumJugadores());
        toUpdate.setParticipaciones(p.getParticipaciones());
        toUpdate.setRonda(p.getRonda());
        toUpdate.setTiempo(p.getTiempo());
        toUpdate.setTurno(p.getTurno());
        toUpdate.setVotosContraCesar(p.getTurno());
        toUpdate.setVotosFavorCesar(p.getVotosFavorCesar());
        partidaRepo.save(toUpdate);
    }
}
