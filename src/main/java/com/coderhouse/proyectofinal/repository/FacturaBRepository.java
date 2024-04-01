package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaBRepository extends JpaRepository<FacturaB,Integer> {
    List<FacturaB> findAllByCuil(int cuil);
}
