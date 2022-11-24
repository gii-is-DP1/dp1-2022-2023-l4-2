package org.springframework.samples.petclinic.mensaje;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensaje extends BaseEntity {

    private String mensaje;

    @ManyToOne
    Jugador jugador;

    @ManyToOne
    Partida partida;
    
}