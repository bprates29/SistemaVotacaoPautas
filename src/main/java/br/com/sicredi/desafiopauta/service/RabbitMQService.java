package br.com.sicredi.desafiopauta.service;

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
	private String MESSAGE_QUEUE;

	public RabbitMQService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendMessage(VotoDto votoDto) {
		log.info("Sending message to queue: " + votoDto);
		rabbitTemplate.convertAndSend(MESSAGE_QUEUE, votoDto);
	}
	

}
