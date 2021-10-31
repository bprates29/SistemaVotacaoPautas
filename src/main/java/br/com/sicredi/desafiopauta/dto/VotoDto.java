package br.com.sicredi.desafiopauta.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class VotoDto implements Serializable {
    private Long id;
    private Long pautaId;
    private Long associadoId;
    private String voto;
    
}
