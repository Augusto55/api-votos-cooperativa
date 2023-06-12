package br.com.meta.apivotoscooperativa.model;

import jakarta.persistence.*;

@Entity
@Table
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer votosTotal;
    private Integer votosSim;
    private Integer votosNao;
    private String expireTime;
}
