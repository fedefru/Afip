package com.afip.afip.repository;

import com.afip.afip.dominio.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto,Long> {
    Impuesto findAllByNombreLike(String name);
    Impuesto findByIdimpuestoLike(Long id);



    List<Impuesto> findByFechaliquidacionBetween(Date fechadesde,Date fechahasta);

}
