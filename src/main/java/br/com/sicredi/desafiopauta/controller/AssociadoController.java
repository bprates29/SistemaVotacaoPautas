package br.com.sicredi.desafiopauta.controller;

import br.com.sicredi.desafiopauta.dto.AssociadoDto;
import br.com.sicredi.desafiopauta.entity.Associado;
import br.com.sicredi.desafiopauta.repository.AssociadoRepository;
import br.com.sicredi.desafiopauta.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("associado")
public class AssociadoController {
    
    @Autowired
    private AssociadoService associadoService;
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<AssociadoDto>> getAllAssociados () {
        return new ResponseEntity<>(associadoService.getAllAssociados(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<AssociadoDto> getAssociadoById (@PathVariable Long id) {
        return new ResponseEntity<>(associadoService.getAssociadosById(id), HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<AssociadoDto> save (@Valid @RequestBody AssociadoDto associadoDto) {
        return new ResponseEntity<>(associadoService.addNewAssociado(associadoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit (@PathVariable("id") Long id,
                           @Valid @RequestBody AssociadoDto associadoDto) {
        associadoService.updateAssociado(id,associadoDto);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping() 
    public ResponseEntity<AssociadoDto> delete (@PathVariable("id") Long id){
        return new ResponseEntity<>(associadoService.deleteAssociado(id), HttpStatus.OK);
    }
}
