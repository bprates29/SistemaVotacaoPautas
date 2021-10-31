package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.client.CpfValidatorClient;
import br.com.sicredi.desafiopauta.dto.AcessoCpf;
import br.com.sicredi.desafiopauta.dto.AssociadoDto;
import br.com.sicredi.desafiopauta.entity.Associado;
import br.com.sicredi.desafiopauta.exception.NotAllowedException;
import br.com.sicredi.desafiopauta.exception.NotFoundException;
import br.com.sicredi.desafiopauta.mappers.AssociadoMapper;
import br.com.sicredi.desafiopauta.repository.AssociadoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoService {

    public static final String ASSOCIADO_PERMITIDO_PARA_VOTO = "ABLE_TO_VOTE";
    @Autowired
    AssociadoRepository associadoRepository;
    
    @Autowired
    CpfValidatorClient cpfValidatorClient;
    
    @Autowired
    AssociadoMapper associadoMapper;
    
    public List<AssociadoDto> getAllAssociados () {
        return  associadoMapper.AssociadoToAssociadoDto(associadoRepository.findAll());
    }

    public AssociadoDto getAssociadosById(Long id) {
        return associadoMapper.AssociadoToAssociadoDto(associadoRepository.getById(id));
    }

    public AssociadoDto addNewAssociado(AssociadoDto associadoDto) {
        try {
            AcessoCpf acessoCpf = cpfValidatorClient.consultaValidCpf(associadoDto.getCpf());
            if (ASSOCIADO_PERMITIDO_PARA_VOTO.equals(acessoCpf.getStatus())) {
                return associadoMapper.AssociadoToAssociadoDto(
                        associadoRepository.save(
                                associadoMapper.AssociadoDtoToAssociado(associadoDto)));
            } else {
                throw new NotAllowedException("Não permitido voto para esse CPF!");
            }
        } catch (FeignException.NotFound ex) {
            throw new NotFoundException("CPF Inválido!");
        }
    }

    public void updateAssociado(Long id, AssociadoDto associadoDto) {
        associadoDto.setId(id);
        associadoRepository.save(associadoMapper.AssociadoDtoToAssociado(associadoDto));
    }

    public AssociadoDto deleteAssociado(Long id) {
        Associado associado = associadoRepository.getById(id);
        associadoRepository.delete(associado);
        return associadoMapper.AssociadoToAssociadoDto(associado);
    }
}
