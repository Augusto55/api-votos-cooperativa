package br.com.meta.apivotoscooperativa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 3, max = 30, message = "O nome do associado deve ter entre 3 e 30 caracteres")
    @Column(nullable = false)
    private String nome;

    public Associado() {
    }

    public Associado(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
