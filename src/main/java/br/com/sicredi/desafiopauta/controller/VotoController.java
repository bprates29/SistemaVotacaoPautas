package br.com.sicredi.desafiopauta.controller;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("voto")
public class VotoController {
    
    @Autowired
    VotoService votoService;

    @PatchMapping("abrir/pautaid/{id}")
    public ResponseEntity<String> edit (@PathVariable("id") Long id) {
        return new ResponseEntity<>(votoService.abrirVotacao(id), HttpStatus.OK);
    }
}
