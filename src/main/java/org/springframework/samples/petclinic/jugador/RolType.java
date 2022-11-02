package org.springframework.samples.petclinic.jugador;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "rol")
public class RolType extends NamedEntity{
}
