package org.springframework.samples.idusmartii.partida;

import javax.persistence.Embeddable;

@Embeddable
public class Sufragio {
    private long votosFavorCesar;
    private long votosContraCesar;
    private long limite;
}
