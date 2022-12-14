package org.springframework.samples.petclinic.estadistica;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorRepository;
import org.springframework.samples.petclinic.partida.FaccionType;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadisticaGeneralService {

    private PartidaRepository partidaRepo;
    private JugadorRepository jugadorRepo;


    @Autowired
    public EstadisticaGeneralService(PartidaRepository partidaRepo, JugadorRepository jugadorRepo){
        this.partidaRepo = partidaRepo;
        this.jugadorRepo = jugadorRepo;
    }

    @Transactional(readOnly = true)
    public List<Partida> getPartidasNoActivas(){
        return partidaRepo.findPartidasNoActivas();
    }

    @Transactional(readOnly = true)
    public List<Jugador> getJugadores(){
        return jugadorRepo.findAll();
    }

    public List<Jugador> getTopJugadoresConVictorias(List<Jugador> jugadores) {
        List<Jugador> res = jugadores.stream()
                                     .sorted(Comparator.comparing(x-> x.getPartidasGanadas(), Comparator.reverseOrder()))
                                     .limit(5)
                                     .collect(Collectors.toList());
        return res;
    }

    public List<Jugador> getTopJugadoresConPartidas(List<Jugador> jugadores) {
        List<Jugador> res = jugadores.stream()
                                     .sorted(Comparator.comparing(x-> x.getPartidasJugadas(), Comparator.reverseOrder()))
                                     .limit(5)
                                     .collect(Collectors.toList());
        return res;
    }
   
    public Long getMaxVotosEnContraCesar(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Long result = 0L;
        for(Partida partida: partidas){
            if(Long.valueOf(partida.getVotosContraCesar())!=null){
                if(partida.getVotosContraCesar() > result){
                    result = partida.getVotosContraCesar();
                }
            }
        }
        return result;
    }

    public Long getMaxVotosAFavorCesar(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Long result = 0L;
        for(Partida partida: partidas){
            if(Long.valueOf(partida.getVotosFavorCesar())!=null){
                if(partida.getVotosFavorCesar() > result){
                    result = partida.getVotosFavorCesar();
                }
            }
        }
        return result;
    }

    public Float getMediaMinutosPartida(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Float result = 0f;
        Float tiempoPartidas = 0f;
        for(Partida partida: partidas){
            if(Long.valueOf(partida.getTiempo())!=null){
                if(partida.getTiempo() > 0){
                    tiempoPartidas += partida.getTiempo();
                }
            }
        }
        result = Float.valueOf(Math.round(tiempoPartidas/partidas.size()));
        return result;
    }

    public Long getVictoriasLeal(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
                if (partida.getFaccionGanadora().getName().equals("Leal")){
                    result++;
                }
            }
        }
        return result;
    }

    public Long getVictoriasTraidor(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
                if (partida.getFaccionGanadora().getName().equals("Traidor")){
                    result++;
                }
            }
        }
        return result;
    }

    public Long getVictoriasMercader(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Long result = 0L;
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
                if (partida.getFaccionGanadora().getName().equals("Mercader")){
                    result++;
                }
            }
        }
        return result;
    }

    public String getFaccionPerdedora(List<Partida> partidas) {
        List<FaccionType> facciones = partidaRepo.findAllFaccionType();
        facciones = facciones.stream().filter(x->!x.getName().equals("No decidido"))
                                      .collect(Collectors.toList());
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Map<FaccionType,Integer> aux = new HashMap<FaccionType, Integer>();
        facciones.forEach(x->aux.put(x, 0));
        for(Partida partida: partidas){
            if(partida.getFaccionGanadora()!=null){
                if (aux.containsKey(partida.getFaccionGanadora())){
                    aux.put(partida.getFaccionGanadora(), aux.get(partida.getFaccionGanadora()) + 1);
                }
            }
        }
        String res = aux.entrySet().stream().min(Comparator.comparing(x->x.getValue()))
                                             .get()
                                             .getKey()
                                             .getName();
        return res;
    }

    public Long getMaxDifVotos(List<Partida> partidas) {
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        Long result = 0L;
        Long res = 0L;
        for(Partida partida: partidas){
            if(Long.valueOf(partida.getVotosContraCesar())!=null && Long.valueOf(partida.getVotosFavorCesar())!=null){
                if(partida.getVotosFavorCesar()>0 || partida.getVotosContraCesar()>0){
                    res = Math.abs(partida.getVotosFavorCesar() - partida.getVotosContraCesar());
                }
                if( res > result){
                    result = res;
                }
            }

        }
        return result;
    }

    public Long getNumPartidas(List<Partida> partidas){
        partidas = partidas.stream().filter(x->!x.getParticipaciones().isEmpty())
                                    .filter(x->!x.getActiva())
                                    .collect(Collectors.toList());
        return Long.valueOf(partidas.size());
    }
}
