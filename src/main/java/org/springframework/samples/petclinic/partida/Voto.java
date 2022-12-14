package org.springframework.samples.petclinic.partida;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Voto extends BaseEntity{

    @ManyToOne
    FaccionType faccion;
    
    private long ronda;
    private long turno;

    @ManyToOne
    Jugador jugador;

    @ManyToOne
    Partida partida;

    private Boolean elegido;

    

}
