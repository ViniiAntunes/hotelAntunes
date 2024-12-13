package com.primeira.appSpring.repository;

import com.primeira.appSpring.model.M_Locacao;
import com.primeira.appSpring.model.M_ViewLocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface R_Locacao extends JpaRepository<M_Locacao, Long> {
    @Query(value = "select hotelaria.locacao.* " +
            "from hotelaria.locacao " +
            "where locacao.quarto_id = :quarto_id " +
            "and (locacao.checkin between :checkin and :chekout " +
            "or :checkin between locacao.checkin and locacao.chekout " +
            "or :chekout between locacao.checkin and locacao.chekout) " +
            "limit 1",nativeQuery = true)
    M_Locacao quartoEstaLocado(@Param("quarto_id") Long id,
                                        @Param("checkin") LocalDateTime checkin,
                                        @Param("chekout") LocalDateTime chekout);

    @Query(value="SELECT  " +
            "hotelaria.quarto.nome, " +
            "hotelaria.locacao.preco, " +
            "hotelaria.locacao.senha, " +
            "hotelaria.locacao.checkin, " +
            "hotelaria.locacao.chekout, " +
            "CASE  " +
            "WHEN CAST(hotelaria.locacao.chekout AS DATE) - CAST(hotelaria.locacao.checkin AS DATE) = 0  " +
            "THEN 1  " +
            "ELSE CAST(hotelaria.locacao.chekout AS DATE) - CAST(hotelaria.locacao.checkin AS DATE)  " +
            "END AS diarias " +
            "FROM hotelaria.locacao " +
            "INNER JOIN hotelaria.quarto ON hotelaria.locacao.quarto_id = hotelaria.quarto.id " +
            "WHERE hotelaria.locacao.hospede_id = :hospede_id",
            nativeQuery = true)
    List<M_ViewLocacao> getLocacoesEmCurso(@Param("hospede_id") Long id_usuario);


    @Query(value="SELECT  " +
            "hotelaria.quarto.nome, " +
            "hotelaria.locacao.preco, " +
            "hotelaria.locacao.senha, " +
            "hotelaria.locacao.checkin, " +
            "hotelaria.locacao.chekout, " +
            "CASE  " +
            "WHEN CAST(hotelaria.locacao.chekout AS DATE) - CAST(hotelaria.locacao.checkin AS DATE) = 0  " +
            "THEN 1  " +
            "ELSE CAST(hotelaria.locacao.chekout AS DATE) - CAST(hotelaria.locacao.checkin AS DATE)  " +
            "END AS diarias " +
            "FROM hotelaria.locacao " +
            "INNER JOIN hotelaria.quarto ON hotelaria.locacao.quarto_id = hotelaria.quarto.id " +
            "WHERE hotelaria.locacao.hospede_id = :hospede_id"+
           " AND NOW() < hotelaria.chekout", nativeQuery = true)
    List<M_ViewLocacao> getLocacoesFuturas(@Param("hospede_id") long hospede_id);

    @Query(value="SELECT  " +
            "hotelaria.quarto.nome, " +
            "hotelaria.locacao.preco, " +
            "hotelaria.locacao.senha, " +
            "hotelaria.locacao.checkin, " +
            "hotelaria.locacao.chekout, " +
            "CASE  " +
            "WHEN CAST(hotelaria.locacao.chekout AS DATE) - CAST(hotelaria.locacao.checkin AS DATE) = 0  " +
            "THEN 1  " +
            "ELSE CAST(hotelaria.locacao.chekout AS DATE) - CAST(hotelaria.locacao.checkin AS DATE)  " +
            "END AS diarias " +
            "FROM hotelaria.locacao " +
            "INNER JOIN hotelaria.quarto ON hotelaria.locacao.quarto_id = hotelaria.quarto.id " +
            "WHERE hotelaria.locacao.hospede_id = :hospede_id"+
            "AND NOW() > hotelaria.chekout", nativeQuery = true)
    List<M_ViewLocacao> getLocacoesRealizadas(@Param("hospede_id") long hospede_id);

}
