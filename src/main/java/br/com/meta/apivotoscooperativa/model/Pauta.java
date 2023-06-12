package br.com.meta.apivotoscooperativa.model;

import jakarta.persistence.*;

@Table
@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String titulo;
    private String descricao;
    private String resultadoSessao;

    public Pauta() {
    }

    public Pauta(Integer id, String titulo, String descricao, String resultadoSessao) {
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

    public String getResultadoSessao() {
        return resultadoSessao;
    }

    public void setResultadoSessao(String resultadoSessao) {
        this.resultadoSessao = resultadoSessao;
    }
}
