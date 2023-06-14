package br.com.meta.apivotoscooperativa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "sessao_votacao")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer votosTotal = 0;

    @Column(nullable = false)
    private Integer votosSim = 0;
    @Column(nullable = false)
    private Integer votosNao = 0;

    @Column(nullable = false)
    private Integer duration = 1;


    @Column(nullable = false)
    private Boolean isOpen = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expireAt;

    @Column(name = "pauta_id", insertable = false, updatable = false)
    private Integer pautaId;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    public SessaoVotacao() {
    }

    public SessaoVotacao(Integer duration) {
        this.duration = duration;
    }

    public SessaoVotacao(Integer id, Integer votosTotal, Integer votosSim, Integer votosNao, Integer duration, Boolean isOpen, Date expireAt) {
        this.id = id;
        this.votosTotal = votosTotal;
        this.votosSim = votosSim;
        this.votosNao = votosNao;
        this.duration = duration;
        this.isOpen = isOpen;
        this.expireAt = expireAt;
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

    public Duration getDuration() {
        return Duration.ofMinutes(duration);
    }


    public void setDuration(Integer duration) {
        this.duration = duration;

        if (duration != null) {
            this.expireAt = new Date(new Date().getTime() + duration * 1000);
        }
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen() {
        this.isOpen = !isOpen;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
        this.pautaId = pauta != null ? pauta.getId() : null;
    }

    public Integer getPautaId() {
        return pautaId;
    }

}