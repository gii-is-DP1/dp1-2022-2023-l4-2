package org.springframework.samples.idusmartii.jugador;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.idusmartii.model.NamedEntity;

@Entity
@Table(name = "rol")
public class RolType extends NamedEntity{
}
