package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaCRepository extends JpaRepository<FacturaC,Integer> {
    //List<FacturaC> findAllByCuil(int cuil);
}
