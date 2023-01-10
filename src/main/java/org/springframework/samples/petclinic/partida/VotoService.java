package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;
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

    @Transactional(rollbackFor = VotoNoPermitidoException.class)
    public void saveVoto(Voto v,Jugador j) throws VotoNoPermitidoException{
        try{
            if(null!=j && v.getPartida()!=null){
                List<Voto> votosRondaTurno = getVotosTurnoJugador(v.getPartida(), j); 
                Voto voto = getVotoById(v.getId().longValue()).orElse(null);
                if(j.getRol().getName().equals("Pretor") && voto != null){
                    votoRepository.save(v);
                }else if(!j.getRol().getName().equals("Edil")){
                    throw new VotoNoPermitidoException();
                }else if(j.getRol().getName().equals("Edil")&& !votosRondaTurno.isEmpty() && votosRondaTurno.get(0).getElegido()){
                    v.setElegido(false);
                    votoRepository.save(v);
                }else if(j.getRol().getName().equals("Edil")&&!votosRondaTurno.isEmpty()){
                    throw new VotoNoPermitidoException();                    
                }
            }
            votoRepository.save(v);
        }catch(Exception e){
            throw new VotoNoPermitidoException();
        }
    }
    
    @Transactional(readOnly = true)
    public List<Voto> getVotosTurnoJugador(Partida p, Jugador j) throws Exception{
        if (p.getJugadores().contains(j)){
            return votoRepository.findVotosTurnoJugador(j, p.getTurno(), p.getRonda(),p);
        }
        else {
            throw new Exception("El usuario" + j.getUser().getUsername() + " no ha jugado la partida");
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

    @Transactional(readOnly = true)
    public Optional<Voto> getVotoElegidoRondaTurno(Partida p){
        return votoRepository.findVotosTurnoElegidos(p.getTurno(), p.getRonda(), p);
    }

    public void CrearVoto(Jugador j, FaccionType faccion, Partida p, Integer maxVoto) throws VotoNoPermitidoException{
        Voto v = new Voto();
        v.setId(maxVoto+1);
        v.setFaccion(faccion);
        v.setJugador(j);
        v.setPartida(p);
        v.setRonda(p.getRonda());
        v.setTurno(p.getTurno());
        saveVoto(v, j);
    }

}
