package br.com.meta.apivotoscooperativa.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
    private String resultadoSessao = "Em votação";

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
