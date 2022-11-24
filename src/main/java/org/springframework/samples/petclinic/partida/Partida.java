package org.springframework.samples.petclinic.partida;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.samples.petclinic.jugador.Jugador;

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
    @Range(min=5,max=8)
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

    @ManyToMany
    //@Size(min=5,max=8)
    private List<Jugador> jugadores;

    @OneToMany(cascade = CascadeType.ALL)
    List<Participacion> participaciones;

    private Boolean activa;

    private Integer fase;
    //Si fase=1 el pretor acaba de terminar.
    
    @ManyToOne
	@JoinColumn
    public FaccionType faccionGanadora2(){
        FaccionType res = null;
        res.setId(4);
        res.setName("No decidido");
        if(this.tiempo == 0 || this.turno == 6 && this.ronda == 2){
            if(this.getVotosFavorCesar() - this.getVotosContraCesar() > 1 && Math.max(this.getVotosContraCesar(), this.getVotosFavorCesar()) <= this.limite){
                res.setName("Leal");
                res.setId(0);
            }
            else if(this.getVotosContraCesar() - this.getVotosFavorCesar() > 1 && Math.max(this.getVotosContraCesar(), this.getVotosFavorCesar()) <= this.limite){
                res.setName("Traidor");
                res.setId(2);
            }
            else if(Math.abs(this.getVotosContraCesar() - this.getVotosFavorCesar()) <= 1 || Math.max(this.getVotosContraCesar(), this.getVotosFavorCesar()) > this.limite ){
                res.setName("Mercader");
                res.setId(3);
            }
        }
        return res;
    }
    
}
