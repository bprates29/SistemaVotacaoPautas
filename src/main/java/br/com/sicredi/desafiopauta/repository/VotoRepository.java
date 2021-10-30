package br.com.sicredi.desafiopauta.repository;

import br.com.sicredi.desafiopauta.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto,Long> {
}
