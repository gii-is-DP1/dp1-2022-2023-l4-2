package org.springframework.samples.petclinic.jugador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.partida.Participacion;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Jugador extends Person{
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username",referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn
    private RolType rol;

    private boolean estaEnPartida;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "jugadores")
    public List<Partida> partidas;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Participacion> participaciones;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    public List<Jugador> amigoDe;

    public Integer getPartidasJugadas() {
        return getPartidas().size();
    }
    
    public Integer getPartidasGanadas() {
        Integer res = 0;
        List<Partida> partidas = getPartidas();
        List<Participacion> participaciones = getParticipaciones();
        for (Partida partida : partidas) {
            for (Participacion participacion : participaciones) {
                if (partida.getParticipaciones().contains(participacion)
                        && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())) {
                    res++;
                }
            }
        }
        return res;
    }
    
    public Integer getVictoriasComoLeal() {
        Integer res = 0;
        List<Partida> partidas = getPartidas();
        List<Participacion> participaciones = getParticipaciones();
        for (Partida partida : partidas) {
            for (Participacion participacion : participaciones) {
                if (partida.getParticipaciones().contains(participacion)
                        && participacion.getFaccionApoyada().getName().equals("Leal")
                        && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())) {
                    res++;
                }
            }
        }
        return res;
    }
    
    public Integer getVictoriasComoTraidor() {
        Integer res = 0;
        List<Partida> partidas = getPartidas();
        List<Participacion> participaciones = getParticipaciones();
        for (Partida partida : partidas) {
            for (Participacion participacion : participaciones) {
                if (partida.getParticipaciones().contains(participacion)
                        && participacion.getFaccionApoyada().getName().equals("Traidor")
                        && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())) {
                    res++;
                }
            }
        }
        return res;
    }
    
    public Integer getVictoriasComoMercader() {
        Integer res = 0;
        List<Partida> partidas = getPartidas();
        List<Participacion> participaciones = getParticipaciones();
        for (Partida partida : partidas) {
            for (Participacion participacion : participaciones) {
                if (partida.getParticipaciones().contains(participacion)
                        && participacion.getFaccionApoyada().getName().equals("Mercader")
                        && partida.getFaccionGanadora().equals(participacion.getFaccionApoyada())) {
                    res++;
                }
            }
        }
        return res;
    }
    
    public Long getTiempoJugado() {
        Long res = 0L;
        List<Partida> partidas = getPartidas();
        for (Partida partida : partidas) {
            res += partida.getTiempo();
        }
        return res;
    }
    
    public String getFaccionFavorita() {
        Integer leal = 0;
        Integer traidor = 0;
        Integer mercader = 0;
        List<Participacion> participaciones = getParticipaciones();
        for (Participacion participacion : participaciones) {
            if (participacion.getFaccionApoyada().getName().equals("Leal")) {
                leal++;
            } else if (participacion.getFaccionApoyada().getName().equals("Traidor")) {
                traidor++;
            } else {
                mercader++;
            }
        }
        Integer max = Math.max(leal, traidor);
        if (Math.max(max, mercader) == leal) {
            return "Leal";
        } else if (Math.max(max, mercader) == traidor) {
            return "Traidor";
        } else {
            return "Mercader";
        }
    }
}