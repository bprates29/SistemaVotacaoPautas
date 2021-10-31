package br.com.sicredi.desafiopauta.controller;

import br.com.sicredi.desafiopauta.dto.PautaComVotosDto;
import br.com.sicredi.desafiopauta.dto.VotoDto;
import br.com.sicredi.desafiopauta.service.PautaService;
import br.com.sicredi.desafiopauta.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@RestController
@RequestMapping("voto")
public class VotoController {
    
    @Autowired
    VotoService votoService;

    @Autowired
    PautaService pautaService;

    @PatchMapping("abrir/pautaid/{id}")
    public ResponseEntity<String> abrirPautaVotacao (@PathVariable("id") Long id) {
        return new ResponseEntity<>(votoService.abrirVotacao(id), HttpStatus.OK);
    }

    @PatchMapping(value = "/associado", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<String> streamMovieEvents(@Valid @RequestBody VotoDto votoDto){
        return votoService.salvarVotoNaFila(votoDto);
    }

    @GetMapping("consultar/pautaid/{id}")
    public ResponseEntity<PautaComVotosDto> consultarVotosByPauta (@PathVariable("id") Long id) {
        return new ResponseEntity<>(pautaService.getVotosByPautaId(id), HttpStatus.OK);
    }
}
