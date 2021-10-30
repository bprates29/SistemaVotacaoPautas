package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.entity.Pauta;
import br.com.sicredi.desafiopauta.mappers.PautaMapper;
import br.com.sicredi.desafiopauta.mappers.PautaMapper;
import br.com.sicredi.desafiopauta.repository.PautaRepository;
import br.com.sicredi.desafiopauta.repository.PautaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;

@Service
public class PautaService {

    private static Logger Logger = LoggerFactory.getLogger(PautaService.class);
    private static DateTimeFormatter Dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
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

    public void iniciarVotacao(PautaDto pautaDto) {
        Logger.info(String.format("Iniciando a votação da pauta %s, inicio: %s", pautaDto.getTitulo(),Dtf.format(LocalDateTime.now())));
        Pauta pauta = pautaRepository.findById(pautaDto.getId()).orElseThrow();
        pauta.setAberta(true);
        pautaRepository.save(pauta);
    }

    public void finalizaVotacao(PautaDto pautaDto) {
        Pauta pauta = pautaRepository.findById(pautaDto.getId()).orElseThrow();
        pauta.setAberta(false);
        pautaRepository.save(pauta);
        Logger.info(String.format("Votação pauta: %s, término: %s", pautaDto.getTitulo(),Dtf.format(LocalDateTime.now())));
    }
    
    public boolean getSeVotacaoEstaAbertaById (Long pautaId) {
        return pautaRepository.findById(pautaId).orElseThrow().getAberta();
    }

    public boolean associadoJaVotouNaPauta(Long associadoId, Long pautaId) {
        /*return pautaRepository.findById(pautaId).orElseThrow()
                .getVotos()
                .parallelStream()
                .anyMatch(voto -> voto.getAssociado().getId().equals(associadoId));*/
        return false;
    }
}
