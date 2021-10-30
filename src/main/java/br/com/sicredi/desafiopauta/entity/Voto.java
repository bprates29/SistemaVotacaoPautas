package br.com.sicredi.desafiopauta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "pauta_id", "associado_id" }) })
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "pauta_id")
    private Long pautaId;
    @Column(name = "associado_id")
    private Long associadoId;
    private boolean voto;
}
