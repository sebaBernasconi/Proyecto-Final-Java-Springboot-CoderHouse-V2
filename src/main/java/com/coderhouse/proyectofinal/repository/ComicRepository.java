package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.product.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicRepository extends JpaRepository<Comic,Integer> {
    Optional<Comic> findByCodigoDeProducto(int codigo);

}
