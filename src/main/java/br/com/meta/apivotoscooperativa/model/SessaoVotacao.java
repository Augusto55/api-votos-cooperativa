package br.com.meta.apivotoscooperativa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "sessao_votacao")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer votosTotal;
    private Integer votosSim;
    private Integer votosNao;
    private String expireTime;

    @JsonBackReference
    @OneToOne(mappedBy = "sessaoVotacao", cascade = CascadeType.ALL)
    private Pauta pauta;

    public SessaoVotacao() {
    }

    public SessaoVotacao(String expireTime) {
        this.expireTime = expireTime;
    }

    public SessaoVotacao(Integer id, Integer votosTotal, Integer votosSim, Integer votosNao, String expireTime) {
        this.id = id;
        this.votosTotal = votosTotal;
        this.votosSim = votosSim;
        this.votosNao = votosNao;
        this.expireTime = expireTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVotosTotal() {
        return votosTotal;
    }

    public void setVotosTotal(Integer votosTotal) {
        this.votosTotal = votosTotal;
    }

    public Integer getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(Integer votosSim) {
        this.votosSim = votosSim;
    }

    public Integer getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(Integer votosNao) {
        this.votosNao = votosNao;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

}