package br.com.sicredi.desafiopauta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PautaComVotosDto {
    
    private PautaDto pautaDto;
    private Integer totalDeVotos;
    private Integer votosSim;
    private Integer votosNao;
    
}
