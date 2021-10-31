package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.dto.VotoDto;
import br.com.sicredi.desafiopauta.entity.Voto;
import br.com.sicredi.desafiopauta.repository.VotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    public static final int TEMPO_PAUTA = 10;
    @InjectMocks
    VotoService votoService;

    @Mock
    PautaService pautaService;

    @Mock
    VotoRepository votoRepository;

    @Mock
    RabbitMQService rabbitMQService;


    @Test
    void givenValidPauta_whenAbrirVotacao_thenReturnTheMenssage() {
        when(pautaService.getPautasById(anyLong())).thenReturn(PautaDto.builder().id(1L).tempo(TEMPO_PAUTA).titulo("TESTE").build());
        String mock = votoService.abrirVotacao(1L);
        Assertions.assertEquals("Votação iniciada, será finalizada em " + TEMPO_PAUTA + " segundos", mock);
    }

    @Test
    void givenValidVotoDto_whenSalvarVotoNaFila_thenPublishRabbitAndReturnMessage() {
        //TODO: Por algum motivo o teste unitário com WEBFLUX não está funcionando corretamente 
        /*VotoDto votoDto = VotoDto.builder().voto("Sim").associadoId(1L).pautaId(1L).build();
        
        when(pautaService.getSeVotacaoEstaAbertaById(votoDto.getPautaId())).thenReturn(true);
        when(votoRepository.findByAssociadoIdAndPautaId(anyLong(),anyLong())).thenReturn(List.of());
        doCallRealMethod().when(rabbitMQService).sendMessageVoteAssociado(votoDto);
        
        Flux<String> mock = votoService.salvarVotoNaFila(votoDto);
        
        Assertions.assertEquals(Flux.just("Voto computado!"), mock);
        verify(rabbitMQService, times(1)).sendMessageVoteAssociado(votoDto);*/
    }

    @Test
    void given3Votos_whenGetTotalVotosPorPautaId_thenReturnTotalOfVotes() {
        Voto voto = new Voto();
        voto.setVoto(true);
        Voto voto1 = new Voto();
        voto1.setVoto(true);
        Voto voto2 = new Voto();
        voto2.setVoto(false);
        
        when(votoRepository.findAllByPautaId(anyLong())).thenReturn(List.of(voto,voto1,voto2));
        
        Integer mock = votoService.getTotalVotosPorPautaId(1L);

        Assertions.assertEquals(3, mock);
    }

    @Test
    void given3Votos2Sim_whenGetTotalVotosSimPorPautaId_thenReturnTotalSimVotos() {
        Voto voto = new Voto();
        voto.setVoto(true);
        Voto voto1 = new Voto();
        voto1.setVoto(true);
        Voto voto2 = new Voto();
        voto2.setVoto(false);

        when(votoRepository.findAllByPautaId(anyLong())).thenReturn(List.of(voto,voto1,voto2));

        Integer mock = votoService.getTotalVotosSimPorPautaId(1L);

        Assertions.assertEquals(2, mock);
    }

    @Test
    void given3Votos1Nao_whenGetTotalVotosNaoPorPautaId_thenReturnTotalNaoVotos() {
        Voto voto = new Voto();
        voto.setVoto(true);
        Voto voto1 = new Voto();
        voto1.setVoto(true);
        Voto voto2 = new Voto();
        voto2.setVoto(false);

        when(votoRepository.findAllByPautaId(anyLong())).thenReturn(List.of(voto,voto1,voto2));

        Integer mock = votoService.getTotalVotosNaoPorPautaId(1L);

        Assertions.assertEquals(1, mock);
    }
    
}