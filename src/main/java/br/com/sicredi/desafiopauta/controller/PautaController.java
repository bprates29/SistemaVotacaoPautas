package br.com.sicredi.desafiopauta.controller;

import br.com.sicredi.desafiopauta.dto.PautaDto;
import br.com.sicredi.desafiopauta.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pauta")
public class PautaController {
    
    @Autowired
    private PautaService pautaService;
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PautaDto>> getAllPautas () {
        return new ResponseEntity<>(pautaService.getAllPautas(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<PautaDto> getPautaById (@PathVariable Long id) {
        return new ResponseEntity<>(pautaService.getPautasById(id), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<PautaDto> save (@Valid @RequestBody PautaDto pautaDto) {
        return new ResponseEntity<>(pautaService.addNewPauta(pautaDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit (@PathVariable("id") Long id,
                           @Valid @RequestBody PautaDto pautaDto) {
        pautaService.updatePauta(id,pautaDto);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{id}") 
    public ResponseEntity<PautaDto> delete (@PathVariable("id") Long id){
        return new ResponseEntity<>(pautaService.deletePauta(id), HttpStatus.OK);
    }
}
