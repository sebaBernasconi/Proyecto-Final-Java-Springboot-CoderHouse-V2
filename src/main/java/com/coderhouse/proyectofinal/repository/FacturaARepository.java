package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaARepository extends JpaRepository<FacturaA,Integer> {
}
