package br.com.meta.apivotoscooperativa.model;

import br.com.meta.apivotoscooperativa.dto.AssociadoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    @Size(min = 11, max = 11)
    private String cpf;

    public Associado() {
    }

    public Associado(AssociadoDto associadoDto) {
        this.id = associadoDto.id();
        this.nome = associadoDto.nome();
        this.cpf = associadoDto.cpf();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf){ this.cpf = cpf;}
}
