package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.entity.Pauta;
import br.com.sicredi.desafiopauta.mappers.PautaMapper;
import br.com.sicredi.desafiopauta.mappers.PautaMapper;
import br.com.sicredi.desafiopauta.repository.PautaRepository;
import br.com.sicredi.desafiopauta.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService {
    
    @Autowired
    PautaRepository pautaRepository;
    
    @Autowired
    PautaMapper pautaMapper;
    
    public List<PautaDto> getAllPautas () {
        return  pautaMapper.PautaToPautaDto(pautaRepository.findAll());
    }

    public PautaDto getPautasById(Long id) {
        return pautaMapper.PautaToPautaDto(pautaRepository.getById(id));
    }

    public PautaDto addNewPauta(PautaDto pautaDto) {
            return pautaMapper.PautaToPautaDto(
                    pautaRepository.save(
                            pautaMapper.PautaDtoToPauta(pautaDto)));
    }

    public void updatePauta(Long id, PautaDto pautaDto) {
        pautaDto.setId(id);
        pautaRepository.save(pautaMapper.PautaDtoToPauta(pautaDto));
    }

    public PautaDto deletePauta(Long id) {
        Pauta pauta = pautaRepository.getById(id);
        pautaRepository.delete(pauta);
        return pautaMapper.PautaToPautaDto(pauta);
    }
}
