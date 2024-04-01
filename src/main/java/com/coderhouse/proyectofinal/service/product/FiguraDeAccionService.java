package com.coderhouse.proyectofinal.service.product;

import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.repository.FiguraDeAccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiguraDeAccionService {

    @Autowired
    private FiguraDeAccionRepository figuraDeAccionRepository;

    public List<FiguraDeAccion>listarFigurasDeAccion(){
        return figuraDeAccionRepository.findAll();
    }

    public FiguraDeAccion guardarFiguraDeAccion(FiguraDeAccion figuraDeAccion){
        return figuraDeAccionRepository.save(figuraDeAccion);
    }

    public FiguraDeAccion editarFiguraDeAccion(int codigoProducto, int nuevoStock,
                                               float nuevoPrecio,FiguraDeAccion figuraDeAccion){
        try {
            if (figuraDeAccionRepository.existsById(codigoProducto)){
                figuraDeAccion.setStock(nuevoStock);
                figuraDeAccion.setPrecio(nuevoPrecio);
                return figuraDeAccionRepository.save(figuraDeAccion);
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public boolean eliminarFiguraDeAccion(int codigoDeProducto){
        try {
            figuraDeAccionRepository.deleteById(codigoDeProducto);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
