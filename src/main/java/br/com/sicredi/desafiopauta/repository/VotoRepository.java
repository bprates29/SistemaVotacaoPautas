package br.com.sicredi.desafiopauta.repository;

import br.com.sicredi.desafiopauta.entity.Associado;
import br.com.sicredi.desafiopauta.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public interface VotoRepository extends JpaRepository<Voto,Long> {

    List<Voto> findByAssociadoIdAndPautaId(Long associado_id, Long pauta_id);

    List<Voto> findAllByPautaId(Long pauta_id);
}
