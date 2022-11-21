package org.springframework.samples.petclinic.jugador;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.h2.store.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
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
        jugadorRepo.save(j);

        userService.saveUser(j.getUser());

        authoritiesService.saveAuthorities(j.getUser().getUsername(),"Jugador");
    }

    @Transactional
    public void editJugador(Jugador j) throws DataAccessException{
        Jugador toUpdate = jugadorRepo.findById(j.getId()).get();
        toUpdate.setFirstName(j.getFirstName());
        toUpdate.setLastName(j.getLastName());
        toUpdate.getUser().setPassword(j.getUser().getPassword());
        jugadorRepo.save(toUpdate);
        userService.saveUser(toUpdate.getUser());
    }
    @Transactional
    public void save2(Jugador j) throws DataAccessException{
        jugadorRepo.save(j);
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
        System.out.println("------------------------------------HOLA SUUUUUUUUUUUUUUUUU --------------------------------");
        System.out.println(username);
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

    
}