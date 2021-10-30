package br.com.sicredi.desafiopauta.mappers;

import br.com.sicredi.desafiopauta.dto.AssociadoDto;
import br.com.sicredi.desafiopauta.entity.Associado;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AssociadoMapper {
    
    AssociadoDto AssociadoToAssociadoDto (Associado associado);

    Associado AssociadoDtoToAssociado (AssociadoDto associadoDto);

    List<AssociadoDto> AssociadoToAssociadoDto (List<Associado> associados);

    List<Associado> AssociadoDtoToAssociado (List<AssociadoDto> associadoDtos);
}
