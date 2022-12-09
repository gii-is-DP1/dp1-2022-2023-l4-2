package org.springframework.samples.petclinic.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.jugador.Jugador;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mensaje{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min=1, max=100)
    private String contenido;

    @ManyToOne
    private Jugador jugador;

    @ManyToOne
    private Chat chat;

    public Mensaje() {
        this.contenido = "";
        this.jugador = null;
        this.chat = null;
    }
    
}