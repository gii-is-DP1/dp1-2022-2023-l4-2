package org.springframework.samples.idusmartii.jugador;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.idusmartii.user.AuthoritiesService;
import org.springframework.samples.idusmartii.user.UserService;
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

    @Transactional(readOnly = true)
    public Jugador getJugadorByUsername(String username){
        return jugadorRepo.findJugadorByUsername(username);
    }

    
}