package br.com.sicredi.desafiopauta.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
public class VotoDto implements Serializable {

    private Long id;
    @NotNull
    private Long pautaId;
    @NotNull
    private Long associadoId;
    @Pattern(message="Valores válidos: Sim ou Não", regexp = "Sim|Não|Nao|sim|não|nao")
    private String voto;
    
}
