package com.coderhouse.proyectofinal.repository;

import com.coderhouse.proyectofinal.model.product.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic,Integer> {
}
