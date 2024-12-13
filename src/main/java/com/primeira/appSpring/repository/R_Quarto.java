package com.primeira.appSpring.repository;

import com.primeira.appSpring.model.M_Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface R_Quarto extends JpaRepository<M_Quarto, Long> {
    @Query(value = "select hotelaria.quarto.* " +
            "from hotelaria.quarto " +
            "left outer join hotelaria.locacao " +
            "on hotelaria.quarto.id = hotelaria.locacao.quarto_id " +
            "and (cast(:checkin as timestamp) between hotelaria.locacao.checkin and hotelaria.locacao.chekout " +
            "or cast(:checkout as timestamp) between hotelaria.locacao.checkin and hotelaria.locacao.chekout " +
            "or hotelaria.locacao.checkin between cast(:checkin as timestamp) and cast(:checkout as timestamp)) " +
            "where hotelaria.locacao.id is null", nativeQuery = true)
    List<M_Quarto> getQuartosDisponiveisPeriodo(@Param("checkin") String checkIn,
                                                @Param("checkout") String checkOut);
}