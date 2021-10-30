package br.com.sicredi.desafiopauta.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;

@Data
public class PautaDto {

    private Long id;
    @NotBlank(message = "Um título para a pauta é obrigatório")
    private String titulo;
    @Value("${tempo.padrao.pauta}")
    private int tempo;
    
}
