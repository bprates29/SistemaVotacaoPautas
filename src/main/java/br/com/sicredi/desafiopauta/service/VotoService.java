package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.dto.VotoDto;
import br.com.sicredi.desafiopauta.mappers.VotoMapper;
import br.com.sicredi.desafiopauta.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class VotoService {
    
    @Autowired
    PautaService pautaService;

    @Autowired
    AssociadoService associadoService;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    VotoMapper votoMapper;
    
    @Autowired
    RabbitMQService rabbitMQService;
    
    public String abrirVotacao (Long pautaId) {
        PautaDto pautaDto = pautaService.getPautasById(pautaId);
        iniciarEFinalizarVotacao(pautaDto);
        return "Votação iniciada, será finalizada em " + pautaDto.getTempo() + " segundos";
    }

    private void iniciarEFinalizarVotacao(PautaDto pautaDto) {
        TimerTask finalizar = new TimerTask() {
            public void run() {
                pautaService.finalizaVotacao(pautaDto);
            }
        };
        Timer timer = new Timer("Timer");
        long tempoDuracao = pautaDto.getTempo() * 1000L;
        pautaService.iniciarVotacao(pautaDto);
        timer.schedule(finalizar, tempoDuracao);
    }
    
    public Flux<String> salvarVotoNaFila(VotoDto votoDto) {
        return Flux.just(votoDto)
                .filter(votoDto1 -> pautaService.getSeVotacaoEstaAbertaById(votoDto1.getPautaId()) &&
                        this.associadoNaoVotouNaPauta(votoDto1.getAssociadoId(),votoDto1.getPautaId()))
                .flatMap(votoDto1 -> {
                    publishEvent(votoDto1);
                    return Flux.just("Voto computado!");
                })
                .defaultIfEmpty("Votação encerrada ou você já votou nessa pauta!");
    }
    
    private boolean associadoNaoVotouNaPauta (Long associadoId, Long pautaId) {
        return votoRepository.findByAssociadoIdAndPautaId(associadoId,pautaId).isEmpty();
    } 

    public void salvarVotoNoBanco(VotoDto votoDto) {
        votoRepository.save(votoMapper.votoDtoToVoto(votoDto));
    }

    private void publishEvent(VotoDto votoDto) {
        rabbitMQService.sendMessage(votoDto);
    }

    
}
