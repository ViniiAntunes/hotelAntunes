package com.primeira.appSpring.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "locacao",
        schema = "hotelaria"
)
public class M_Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime checkin;
    private LocalDateTime chekout;
    private String senha;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "hospede_id", nullable = false)
    private M_Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "quarto_id", nullable = false)
    private M_Quarto quarto;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCheckIn() {
        return checkin;
    }

    public void setCheckIn(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckOut() {
        return chekout;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.chekout = checkOut;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public M_Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(M_Usuario usuario) {
        this.usuario = usuario;
    }

    public M_Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(M_Quarto quarto) {
        this.quarto = quarto;
    }
}
