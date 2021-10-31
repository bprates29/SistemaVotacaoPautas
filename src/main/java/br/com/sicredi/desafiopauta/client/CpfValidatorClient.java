package br.com.sicredi.desafiopauta.client;

import br.com.sicredi.desafiopauta.dto.AcessoCpf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-info", url = "https://user-info.herokuapp.com")
public interface CpfValidatorClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{cpf}")
    AcessoCpf consultaValidCpf(@PathVariable("cpf") String cpf);
}
