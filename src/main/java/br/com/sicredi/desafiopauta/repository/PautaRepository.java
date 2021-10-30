package br.com.sicredi.desafiopauta.repository;

import br.com.sicredi.desafiopauta.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
