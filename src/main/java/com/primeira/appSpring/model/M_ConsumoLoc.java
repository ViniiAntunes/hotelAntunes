package com.primeira.appSpring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumo_loc",
        schema = "hotelaria")
public class M_ConsumoLoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false)
    private M_Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_loc", nullable = false)
    private M_Locacao locacao;

    private Integer quantidade;

    private Double preco;

    private LocalDate data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public M_Produto getProduto() {
        return produto;
    }

    public void setProduto(M_Produto produto) {
        this.produto = produto;
    }

    public M_Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(M_Locacao locacao) {
        this.locacao = locacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
