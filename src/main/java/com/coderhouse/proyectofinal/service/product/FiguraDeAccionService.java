package com.coderhouse.proyectofinal.service.product;

import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.repository.FiguraDeAccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiguraDeAccionService {

    @Autowired
    private FiguraDeAccionRepository figuraDeAccionRepository;

    public List<FiguraDeAccion>listarFigurasDeAccion(){
        return figuraDeAccionRepository.findAll();
    }

    public FiguraDeAccion buscarFiguraPorCodigo(int codigo){
        try {
            Optional<FiguraDeAccion>figuraDeAccion = figuraDeAccionRepository.findById(codigo);
            return figuraDeAccion.orElse(null);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public FiguraDeAccion guardarFiguraDeAccion(FiguraDeAccion figuraDeAccion){
        return figuraDeAccionRepository.save(figuraDeAccion);
    }

    public FiguraDeAccion editarFiguraDeAccion(int codigoProducto, FiguraDeAccion figuraDeAccion{
        //Editar: sacar el stock y el objeto. usar optional y que sea solo modificar precio
        try {
            Optional<FiguraDeAccion> figuraDeAccionActualizada = figuraDeAccionRepository.findById(codigoProducto);
            figuraDeAccionActualizada.orElse(null).editarFiguraDeAccion(figuraDeAccion.getNombre(),
                    figuraDeAccion.getDescripcion(),figuraDeAccion.getFabricante(),figuraDeAccion.isEsArticulado());
            return figuraDeAccionActualizada.orElse(null);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public FiguraDeAccion actualizarStockPostVenta(int codigoProducto){
        try {
            Optional<FiguraDeAccion> figuraDeAccionActualizar = figuraDeAccionRepository.findById(codigoProducto);
            figuraDeAccionActualizar.orElse(null).actualizarStock();
           return figuraDeAccionRepository.save(figuraDeAccionActualizar.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public FiguraDeAccion modificarPrecio(int codigoDeProducto, float nuevoPrecio){
        try {
            Optional<FiguraDeAccion> figuraDeAccion = figuraDeAccionRepository.findById(codigoDeProducto);
            figuraDeAccion.orElse(null).modificarPrecio(nuevoPrecio);
            return figuraDeAccionRepository.save(figuraDeAccion.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
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
