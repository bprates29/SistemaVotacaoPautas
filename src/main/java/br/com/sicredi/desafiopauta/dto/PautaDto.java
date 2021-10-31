package br.com.sicredi.desafiopauta.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PautaDto {

    public static final int TEMPO_PADRAO = 60;
    
    private Long id;
    @NotBlank(message = "Um título para a pauta é obrigatório")
    private String titulo;
    @Min(value=1, message = "Tempo de votação tem que ser positivo e maior que 1 segundo" )
    private int tempo = TEMPO_PADRAO; //em segundos
    
    
}
