package br.com.meta.apivotoscooperativa.model;

import br.com.meta.apivotoscooperativa.commons.enums;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Table
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;

    @Column(name = "resultadoSessao")
    @Enumerated(EnumType.STRING)
    private enums.PautaStatus resultadoSessao = enums.PautaStatus.PENDENTE;

    @JsonManagedReference
    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessaoVotacao> sessaoVotacao = new ArrayList<>();

    public Pauta() {
    }

    public Pauta(Integer id, String titulo, String descricao, enums.PautaStatus resultadoSessao) {
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

    public void addSessaoVotacao(SessaoVotacao sessao) {
        sessaoVotacao.add(sessao);
        sessao.setPauta(this);
    }

    public void removeSessaoVotacao(SessaoVotacao sessao) {
        sessaoVotacao.remove(sessao);
        sessao.setPauta(null);
    }

}