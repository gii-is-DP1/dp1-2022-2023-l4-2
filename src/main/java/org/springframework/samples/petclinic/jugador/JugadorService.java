package org.springframework.samples.petclinic.jugador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.partida.Participacion;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JugadorService {
    private JugadorRepository jugadorRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;
    
    @Autowired
    public JugadorService(JugadorRepository jugadorRepo){
        this.jugadorRepo = jugadorRepo;
    }

    PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @Transactional(readOnly = true)
    public List<Jugador> getJugadores(){
        return jugadorRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Jugador> getJugadorById(Integer id){
        return jugadorRepo.findById(id);
    }
    
    @Transactional
    public void deleteJugador(Integer id){
        jugadorRepo.deleteById(id);

    }

    @Transactional
    public void saveJugador(Jugador j) throws DataAccessException{

        j.setYaElegido(false);

        jugadorRepo.save(j);

        userService.saveUser(j.getUser());

        authoritiesService.saveAuthorities(j.getUser().getUsername(),"jugador");
    }

    @Transactional
    public void editJugador(Jugador j) throws DataAccessException{
        Jugador toUpdate = jugadorRepo.findById(j.getId()).get();
        toUpdate.setFirstName(j.getFirstName());
        toUpdate.setLastName(j.getLastName());
        toUpdate.getUser().setPassword(passwordEncoder.encode(j.getUser().getPassword()));
        toUpdate.setLastModifiedDate(j.getLastModifiedDate());
        toUpdate.setModifier(j.getModifier());
        jugadorRepo.save(toUpdate);
        userService.saveUser(toUpdate.getUser());
    }
    @Transactional
    public void save2(Jugador j) throws DataAccessException{
        jugadorRepo.save(j);//Necesitamos este método porque el otro save, tambien guarda las autoridades y el usuario, este lo usamos para solo modificar datos
    }

    @Transactional
    public void agregarAmigo(Jugador j, String userPrincipal) throws DataAccessException{
        Jugador toUpdate = jugadorRepo.findById(j.getId()).get();
        List<Jugador> amigos = jugadorRepo.findJugadorByUsername(userPrincipal).getAmigoDe();
        if(!amigos.contains(j)){
            amigos.add(j);
            jugadorRepo.save(toUpdate);
        }
    }

    @Transactional
    public void deleteAmigo(String username1, String username2) throws Exception{
        Jugador toUpdate = jugadorRepo.findJugadorByUsername(username1);
        List<Jugador> amigos = toUpdate.getAmigoDe();
        Jugador amigoABorrar = jugadorRepo.findJugadorByUsername(username2);
        if(amigos.contains(amigoABorrar)){
            amigos.remove(amigoABorrar);
            toUpdate.setAmigoDe(amigos);
            jugadorRepo.save(toUpdate);
        } else {
            throw new Exception("Los usuarios " + username1 + " y " + username2 + " no son amigos");
        }
    }

    @Transactional(readOnly = true)
    public Jugador getJugadorByUsername(String username){
        return jugadorRepo.findJugadorByUsername(username);
    }

    @Transactional(readOnly = true)
    public Collection<Jugador> getJugadoresByUsername(String username){
        return jugadorRepo.findJugadoresByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<Partida> getPartidasActivasAmigos(Jugador j){
        List<Jugador> amigos = j.getAmigoDe();
        List<Partida> partidasAmigos = new ArrayList<Partida>();
        for(Jugador amigo : amigos){
            List<Partida> aux = amigo.getPartidas();

            for(Partida p : aux){
                if((!partidasAmigos.contains(p)) && p.getActiva()!= null && p.getActiva()){
                    partidasAmigos.add(p);
                }
            }
        }
        return partidasAmigos;
    }

    @Transactional(readOnly=true)
    public List<RolType> getRoles(){
        return jugadorRepo.findRoles();
    }

    public void preparaRolesRonda2(Partida p){
        List<RolType> roles = getRoles();
        RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
        RolType sinRol = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
        
        for(int i = 0;i<p.getJugadores().size();i++){
            Jugador j = p.getJugadores().get(i);
            if(j.getParticipacionEnPartida(p).getNumConsul() == p.getTurno()){
                j.setRol(consul);
            }else{
                j.setRol(sinRol);
            }
            //save2(j);
        }
    }

    public void añadePartidaAJugador(Partida p,Jugador j){
        if(!j.getPartidas().contains(p)){
            List<Partida> ls = j.getPartidas();
            ls.add(p);
            j.setPartidas(ls);
            saveJugador(j);
        }
    }

    public void reparteRoles(Partida p){
        List<RolType> roles = getRoles();
        for(int i=0;i<p.getJugadores().size();i++){
            Jugador j =p.getJugadores().get(i);
            Participacion part = j.getParticipacionEnPartida(p);
            RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
            RolType pretor= roles.stream().filter(x->x.getName().equals("Pretor")).findAny().get();
            RolType edil = roles.stream().filter(x->x.getName().equals("Edil")).findAny().get();
            RolType sinRol = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
            if(part.getNumConsul()== p.getTurno()){
                j.setRol(consul);
            }else if(part.getNumConsul() == (p.getTurno()+1)%p.getNumJugadores()){
                j.setRol(pretor);
            }else if(part.getNumConsul() == (p.getTurno()+2)%p.getNumJugadores() ||part.getNumConsul() == (p.getTurno()+3)%p.getNumJugadores()){
                j.setRol(edil);
            }else{
                j.setRol(sinRol);
            }
            if(part.getNumConsul() == p.getNumJugadores()){
                if(part.getNumConsul() == p.getTurno()){
                    j.setRol(consul);
                }else if(part.getNumConsul()-1 == p.getTurno()){
                    j.setRol(pretor);
                }else if(part.getNumConsul()-2 == p.getTurno() || part.getNumConsul()-3 == p.getTurno()){
                    j.setRol(edil);
                }else{
                    j.setRol(sinRol);
                }
            }
        }
    }

    public void reestableceRolesDeNoElegidos(Partida p,RolType consul,RolType sinRol){
        Integer numElegidos = p.getJugadores().stream().mapToInt(x->x.getYaElegido()?1:0).sum();
        if(numElegidos == 3){
            for(int i = 0;i<p.getJugadores().size();i++){
                Jugador jug = p.getJugadores().get(i);
                if(p.getRonda()==2&&p.getTurno() == 1&&jug.getRol().getName().equals("Consul")){
                        jug.setRol(consul);
                }else if(!jug.getYaElegido()){
                    jug.setRol(sinRol);
                }
            }
        }
    }

    public List<Jugador> jugadoresQuePuedenSerEdil(Partida p){
        List<Jugador> jugadores = p.getJugadores();
        List<Jugador> jugadoresFiltrado = List.of();
        if (p.getNumJugadores() == 5) {
            jugadoresFiltrado = jugadores.stream()
                    .filter(x-> x.getRol() != null).filter(x-> !x.getRol().getName().equals("Consul"))
                    .filter(x-> x.getYaElegido() != true)
                    .collect(Collectors.toList());
        } else {
            jugadoresFiltrado = jugadores.stream()
            .filter(x-> x.getRol() != null).filter(x-> !x.getRol().getName().equals("Edil"))
            .filter(x-> !x.getRol().getName().equals("Consul")).filter(x-> x.getYaElegido() != true)
            .collect(Collectors.toList());
        }
        return jugadoresFiltrado;
    }

    public void consulRonda2(Partida p, List<RolType> roles){
        if(p.getTurno()>=2 && p.getRonda()==2){
            for(int i=0;i<p.getJugadores().size();i++){
                RolType consul = roles.stream().filter(x->x.getName().equals("Consul")).findAny().get();
                Jugador jug = p.getJugadores().get(i);
                RolType sr = roles.stream().filter(x->x.getName().equals("Sin rol")).findAny().get();
                if(jug.getParticipacionEnPartida(p).getNumConsul() == p.getTurno()){
                    jug.setRol(consul);
                }else if(jug.getRol().getName().equals("Consul")){
                    jug.setRol(sr);
                }
            }
        }
    }

    public void cambiaRonda(Partida p){
        if(p.getTurno()>p.getNumJugadores()){
            p.setRonda(p.getRonda()+1);
            p.setTurno(1);
            preparaRolesRonda2(p);
        }
    }

    public void guardaJugadoresPartida(Partida p){
        for(int i = 0;i<p.getNumJugadores();i++){
            save2(p.getJugadores().get(i));
        }
    }

    @Transactional(readOnly = true)
    public List<Jugador> getJugadoresPageables(Pageable pageable){
        return jugadorRepo.findAllPageable(pageable);
    }
}