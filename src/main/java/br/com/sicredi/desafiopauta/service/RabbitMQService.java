package br.com.sicredi.desafiopauta.service;

import br.com.sicredi.desafiopauta.dto.PautaComVotosDto;
import br.com.sicredi.desafiopauta.dto.VotoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQService {
	
	private final RabbitTemplate rabbitTemplate;

	@Value("${associado.vote.event.queue}")
	private String MESSAGE_QUEUE_VOTE_ASSOCIADO;

	@Value("${finaliza.vote.pauta.event.queue}")
	private String MESSAGE_QUEUE_FINALIZA_VOTOS;

	public RabbitMQService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendMessageVoteAssociado(VotoDto votoDto) {
		log.info("Sending message to queue: " + votoDto);
		rabbitTemplate.convertAndSend(MESSAGE_QUEUE_VOTE_ASSOCIADO, votoDto);
	}

	public void sendMessageFinalizaVotosPauta(PautaComVotosDto pautaComVotosDto) {
		log.info("Sending message to queue: " + pautaComVotosDto);
		rabbitTemplate.convertAndSend(MESSAGE_QUEUE_FINALIZA_VOTOS, pautaComVotosDto);
	}
	

}
