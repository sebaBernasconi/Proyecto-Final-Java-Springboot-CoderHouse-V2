package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.transactions.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Integer> {
}
