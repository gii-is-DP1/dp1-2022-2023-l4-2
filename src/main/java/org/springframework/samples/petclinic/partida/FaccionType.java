package org.springframework.samples.petclinic.partida;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "facciones")
public class FaccionType extends NamedEntity{
    
}
