package org.springframework.samples.petclinic.partida;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participacion extends BaseEntity{
    
    
    
    @ManyToOne
	@JoinColumn
    private FaccionType faccionApoyada;

    private Integer numConsul;

    private boolean esAnfitrion;

    private long votosContraCesar;

    private long votosFavorCesar;

    private long votosNeutros;

    @ManyToOne(cascade =  CascadeType.ALL)
    Partida partidas;

    @ManyToMany
    List<FaccionType> opciones;
}
