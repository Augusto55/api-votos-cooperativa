package br.com.meta.apivotoscooperativa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "associado_sessao_votacao")
public class AssociadoSessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_votacao_id")
    private SessaoVotacao sessaoVotacao;

    public AssociadoSessaoVotacao() {
    }

    public AssociadoSessaoVotacao(Associado associado, SessaoVotacao sessaoVotacao) {
        this.associado = associado;
        this.sessaoVotacao = sessaoVotacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }
}
