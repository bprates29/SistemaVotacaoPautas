package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.AssociadoDto;
import br.com.sicredi.desafiopauta.entity.Associado;
import br.com.sicredi.desafiopauta.mappers.AssociadoMapper;
import br.com.sicredi.desafiopauta.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociadoService {
    
    @Autowired
    AssociadoRepository associadoRepository;
    
    @Autowired
    AssociadoMapper associadoMapper;
    
    public List<AssociadoDto> getAllAssociados () {
        return  associadoMapper.AssociadoToAssociadoDto(associadoRepository.findAll());
    }

    public AssociadoDto getAssociadosById(Long id) {
        return associadoMapper.AssociadoToAssociadoDto(associadoRepository.getById(id));
    }

    public AssociadoDto addNewAssociado(AssociadoDto associadoDto) {
            return associadoMapper.AssociadoToAssociadoDto(
                    associadoRepository.save(
                            associadoMapper.AssociadoDtoToAssociado(associadoDto)));
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
