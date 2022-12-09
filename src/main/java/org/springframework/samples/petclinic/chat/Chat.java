package org.springframework.samples.petclinic.chat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import org.springframework.samples.petclinic.partida.Partida;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    @OneToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;

    public Chat(){
        this.mensajes = new ArrayList<>();
        this.partida = null;
    }
    
}
