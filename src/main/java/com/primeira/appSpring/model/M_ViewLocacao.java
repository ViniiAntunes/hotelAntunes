package com.primeira.appSpring.model;


import java.time.LocalDateTime;

public interface M_ViewLocacao {
    String  getNome();
    Double  getPreco();
    String  getSenha();
    LocalDateTime  getCheckin();
    LocalDateTime getChekout();
    Integer getDiarias();
}
