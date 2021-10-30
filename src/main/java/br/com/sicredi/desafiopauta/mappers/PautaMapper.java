package br.com.sicredi.desafiopauta.mappers;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.entity.Pauta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PautaMapper {
    
    PautaDto PautaToPautaDto (Pauta Pauta);

    Pauta PautaDtoToPauta (PautaDto PautaDto);

    List<PautaDto> PautaToPautaDto (List<Pauta> Pautas);

    List<Pauta> PautaDtoToPauta (List<PautaDto> PautaDtos);
}
