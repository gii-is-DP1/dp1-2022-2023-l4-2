package org.springframework.samples.petclinic.estadistica;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Logro implements Serializable {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
    @NotNull
	private long id;
    @NotBlank
    @Size(min = 3, max = 20)
    @NotNull
    private String nombre;
    private String descripcion;
     
    @ManyToOne
	@JoinColumn
    @NotNull
    private LogrosType tipo;
    @Min(1)
    @NotNull
    private long limite;
    @ManyToOne
	@JoinColumn
    private DificultadType dificultad;

}
