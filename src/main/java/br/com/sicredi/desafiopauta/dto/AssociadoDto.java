package br.com.sicredi.desafiopauta.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AssociadoDto {
    
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "CPF é obrigatório")
    @Setter(AccessLevel.NONE) private String cpf;

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("[-+.^:,]", "");
    }
}
