package br.com.meta.apivotoscooperativa.model;

import br.com.meta.apivotoscooperativa.commons.enums;
import jakarta.persistence.*;


@Table
@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;

    @Column(name="resultadoSessao")
    @Enumerated(EnumType.STRING)
    private enums.PautaStatus resultadoSessao = enums.PautaStatus.PENDENTE;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessao_votacao_id", referencedColumnName = "id")
    private SessaoVotacao sessaoVotacao;

    public Pauta() {
    }

    public Pauta(Integer id, String titulo, String descricao, enums.PautaStatus  resultadoSessao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.resultadoSessao = resultadoSessao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public enums.PautaStatus getResultadoSessao() {
        return resultadoSessao;
    }

    public void setResultadoSessao(enums.PautaStatus resultadoSessao) {
        this.resultadoSessao = resultadoSessao;
    }

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }
}
