package org.springframework.samples.petclinic.estadistica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Logro implements Serializable {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	private long id;
    private String nombre;
    private String descripcion;
    @ManyToOne
	@JoinColumn
    private LogrosType tipo;
    private long limite;
    @ManyToOne
	@JoinColumn
    private DificultadType dificultad;
}
