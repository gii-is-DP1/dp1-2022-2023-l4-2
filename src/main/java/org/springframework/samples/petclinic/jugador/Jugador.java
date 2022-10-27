package org.springframework.samples.petclinic.jugador;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Jugador implements Serializable {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	private long id;
    @NotBlank
    private String nickname;
    @ManyToOne
    @JoinColumn
    private RolType rol;
    private Boolean esAnfitrion;
    //@Max(numJugadores)
    private Integer numConsul;
    private Boolean estaEnPartida;
}