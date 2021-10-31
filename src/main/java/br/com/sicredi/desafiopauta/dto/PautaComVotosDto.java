package br.com.sicredi.desafiopauta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PautaComVotosDto implements Serializable {
    
    private PautaDto pautaDto;
    private Integer totalDeVotos;
    private Integer votosSim;
    private Integer votosNao;
    
}
