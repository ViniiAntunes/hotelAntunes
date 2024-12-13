package com.primeira.appSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_quarto",
    schema="hotelaria")
public class M_Tipo_Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
