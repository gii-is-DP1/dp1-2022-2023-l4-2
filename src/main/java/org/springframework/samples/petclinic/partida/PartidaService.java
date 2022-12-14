package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaService {
    private PartidaRepository partidaRepo;

    @Autowired
    public PartidaService(PartidaRepository partidaRepo, ParticipacionService participacionService){
        this.partidaRepo = partidaRepo;
    }

    @Transactional(readOnly = true)
    public List<Partida> getPartidasPageables(Pageable pageable){
        return partidaRepo.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public List<Partida> getPartidas(){
        return partidaRepo.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Partida> getPartidaById(long id) {
        Optional<Partida> result = partidaRepo.findById(id);
        return result;
    }

    @Transactional(readOnly = true)
    public List<FaccionType> getFaccionesType(){
        return partidaRepo.findAllFaccionType();
    }

    @Transactional(readOnly = true)
    public FaccionType getFaccionesTypeByName(String name){
         return partidaRepo.findFaccionTypeByName(name);
    }
    
    @Transactional
    public void deletePartida(long id) {
        partidaRepo.deleteById(id);
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

    @Transactional(readOnly = true)
    public List<Partida> getPartidasActivas(){
        return this.partidaRepo.findPartidasActivas();
    }
    @Transactional(readOnly = true)
    public List<Partida> getPartidasNoActivas(){
        return this.partidaRepo.findPartidasNoActivas();
    }
    @Transactional
    public Map<Jugador,List<FaccionType>> jugadoresConOpcionesDePartida(Partida p){
        Map<Jugador,List<FaccionType>> res = new HashMap<Jugador,List<FaccionType>>();
        Integer o = 0;
        List<Jugador> jugadores = p.getJugadores();
        List<FaccionType> opcionesJugador = new ArrayList<FaccionType>();
        List<FaccionType> facciones = getFaccionesType();
        opcionesJugador.add(facciones.get(2));
        opcionesJugador.add(facciones.get(2));
        for(int i = 0; i<jugadores.size()-1;i++){
            opcionesJugador.add(facciones.get(0));
            opcionesJugador.add(facciones.get(1));
        }
        for(Jugador j:jugadores){
            res.put(j, new ArrayList<FaccionType>());
            int random1 = getRandomInt(2*jugadores.size()-o);
            res.get(j).add(opcionesJugador.get(random1));
            opcionesJugador.remove(random1);
            o++;
            int random2 = getRandomInt(2*jugadores.size()-o);
            res.get(j).add(opcionesJugador.get(random2));
            opcionesJugador.remove(random2);
            o++;
        }
        return res;
    }

    private Integer getRandomInt(Integer max){
        Double res = Math.floor(Math.random() * max);
        return res.intValue();
    }

    public Voto cambiarVoto(Voto v){
        if(v.getFaccion().getName() == "Leal"){
            v.setFaccion(getFaccionesTypeByName("Traidor"));
        }else{
            v.setFaccion(getFaccionesTypeByName("Leal"));
        }
        return v;
    }
}
