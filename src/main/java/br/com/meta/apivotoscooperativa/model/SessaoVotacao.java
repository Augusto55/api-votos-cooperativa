package br.com.meta.apivotoscooperativa.model;

import br.com.meta.apivotoscooperativa.dto.SessaoVotacaoRegisterDto;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "sessao_votacao")
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    public SessaoVotacao() {
    }

    public SessaoVotacao(SessaoVotacaoRegisterDto SessaoVotacaoRegisterDto) {
        this.duration = SessaoVotacaoRegisterDto.duration();
        this.pautaId = SessaoVotacaoRegisterDto.pautaId();
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

    public void setVotosTotal() {
        this.votosTotal = this.votosTotal + 1;
    }

    public Integer getVotosSim() {
        return votosSim;
    }

    public void setVotosSim() {
        this.votosSim =  this.votosSim + 1;
    }

    public Integer getVotosNao() {
        return votosNao;
    }

    public void setVotosNao() {
        this.votosNao =this.votosNao + 1;
    }

    public Duration getDuration() {
        return Duration.ofMinutes(duration);
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen() {
        this.isOpen = !isOpen;
    }


    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
        this.pautaId = pauta != null ? pauta.getId() : null;
    }

    public Integer getPautaId() {
        return pautaId;
    }

    public boolean isExpired() {
        return new Date().after(this.expireAt);
    }

    public Date getExpireAt() {
        return expireAt;
    }

}