package br.com.sicredi.desafiopauta.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;

@Data
public class PautaDto {

    public static final int TEMPO_PADRAO = 60;
    
    private Long id;
    @NotBlank(message = "Um título para a pauta é obrigatório")
    private String titulo;
    private int tempo = TEMPO_PADRAO; //em segundos
    
}
