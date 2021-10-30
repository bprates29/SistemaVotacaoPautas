package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class VotoService {
    
    @Autowired
    PautaService pautaService;
    
    public String abrirVotacao (Long pautaId) {
        PautaDto pautaDto = pautaService.getPautasById(pautaId);
        iniciarEFinalizarVotacao(pautaDto);
        return "Votação iniciada, será finalizada em " + pautaDto.getTempo();
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
}
