package br.com.sicredi.desafiopauta.listener;

import br.com.sicredi.desafiopauta.dto.VotoDto;
import br.com.sicredi.desafiopauta.service.PautaService;
import br.com.sicredi.desafiopauta.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class VotoEventListener {

    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(VotoEventListener.class);
    private static DateTimeFormatter Dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    @Autowired
    VotoService votoService;
	
	public void receiveMessage(VotoDto message) {
        votoService.salvarVotoNoBanco(message);
        Logger.info(String.format("Received <%s> - %s", message,Dtf.format(LocalDateTime.now())));
    }

}
