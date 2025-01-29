package com.primeira.appSpring.repository;

import com.primeira.appSpring.model.M_Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface R_Produto extends JpaRepository<M_Produto, Long> {
    @Query("SELECT p FROM M_Produto p WHERE p.codBarras = :codBarras")
    M_Produto findByCodBarras(@Param("codBarras") String codBarras);

    @Query("SELECT p FROM M_Produto p WHERE p.descricao LIKE %:Descricao%")
    List<M_Produto> findByDescricao(@Param("Descricao") String Descricao);
}

