package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiguraDeAccionRepository extends JpaRepository<FiguraDeAccion,Integer> {
    Optional<FiguraDeAccion> findByCodigoDeProducto(int codigoProducto);
}
