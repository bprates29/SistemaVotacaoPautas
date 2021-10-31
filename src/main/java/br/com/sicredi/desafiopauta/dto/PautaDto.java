package br.com.sicredi.desafiopauta.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class PautaDto implements Serializable {

    public static final int TEMPO_PADRAO = 60;
    

    private Long id;
    @NotBlank(message = "Um título para a pauta é obrigatório")
    private String titulo;
    @Min(value=1, message = "Tempo de votação tem que ser positivo e maior que 1 segundo" )
    private int tempo = TEMPO_PADRAO; //em segundos
    
    
}
