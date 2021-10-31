package br.com.sicredi.desafiopauta.mappers;

import br.com.sicredi.desafiopauta.dto.VotoDto;
import br.com.sicredi.desafiopauta.entity.Associado;
import br.com.sicredi.desafiopauta.entity.Pauta;
import br.com.sicredi.desafiopauta.entity.Voto;
import br.com.sicredi.desafiopauta.repository.AssociadoRepository;
import br.com.sicredi.desafiopauta.repository.PautaRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel="spring")
public class VotoMapper {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    AssociadoRepository associadoRepository;
    
    public Voto votoDtoToVoto(VotoDto votoDto) {
        Associado associado = associadoRepository.getById(votoDto.getAssociadoId());
        Pauta pauta = pautaRepository.getById(votoDto.getPautaId());
        Voto voto = new Voto();
        voto.setAssociadoId(associado.getId());
        voto.setPautaId(pauta.getId());
        voto.setVoto("Sim".equals(votoDto.getVoto()));
        return voto;
    }
}
