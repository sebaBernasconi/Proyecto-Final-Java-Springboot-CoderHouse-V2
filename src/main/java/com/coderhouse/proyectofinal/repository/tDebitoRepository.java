package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.payment.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tDebitoRepository extends JpaRepository<Debito,Integer> {
}
