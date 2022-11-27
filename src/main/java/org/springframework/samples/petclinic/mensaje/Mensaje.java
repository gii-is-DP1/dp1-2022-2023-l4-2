package org.springframework.samples.petclinic.mensaje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensaje  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=1, max=300)
    private String contenido;

    @ManyToOne
    Jugador jugador;

    @ManyToOne
    Partida partida;

    public Mensaje() {
        this.contenido = "";
        this.jugador = null;
        this.partida = null;
    }
    
}