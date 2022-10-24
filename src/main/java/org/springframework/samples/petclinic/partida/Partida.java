package org.springframework.samples.petclinic.partida;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Partida implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision = 10)
    private long id;
    private long ronda;
    private long turno;
    private long numJugadores;
    private String anfitrion;
    //@Embedded
    //private Sufragio sufragio;
    private long votosFavorCesar;
    private long votosContraCesar;
    private long limite;
    @ManyToOne
	@JoinColumn
    private FaccionType faccionGanadora;
    private long tiempo;
    
}