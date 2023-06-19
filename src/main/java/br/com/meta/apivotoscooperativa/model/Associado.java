package br.com.meta.apivotoscooperativa.model;

import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import jakarta.persistence.*;

@Entity
@Table
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String nome;

    public Associado() {
    }

    public Associado(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Associado(AssociadoDto associadoDto) {
        this.id = associadoDto.id();
        this.nome = associadoDto.nome();
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
