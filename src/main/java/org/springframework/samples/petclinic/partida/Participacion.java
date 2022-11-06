package org.springframework.samples.petclinic.partida;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
}
