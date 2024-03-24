package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.user.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito,Integer> {
}
