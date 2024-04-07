package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.transactions.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer> {
    //List<Venta> findAllByCuil(int cuil);
}
