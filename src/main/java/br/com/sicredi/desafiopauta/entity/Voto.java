package br.com.sicredi.desafiopauta.entity;

import lombok.Data;

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
    @ManyToOne
    @JoinColumn(name="pauta_id", nullable=false)
    private Pauta pauta;
    @ManyToOne
    @JoinColumn(name="associado_id", nullable=false)
    private Associado associado;
    private boolean voto;
}
