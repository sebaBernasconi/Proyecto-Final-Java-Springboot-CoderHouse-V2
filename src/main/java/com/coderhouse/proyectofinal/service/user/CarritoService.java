package com.coderhouse.proyectofinal.service.user;

import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.repository.CarritoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService {

    private CarritoRepository carritoRepository;

    public Carrito guardarCarrito(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    public Carrito pagarCarrito(int codigo, boolean pagado){
        try {
            if (carritoRepository.existsById(codigo)){ //preguntar si no hay drama en usar optional(?
                Optional<Carrito> carritoAPagar = carritoRepository.findById(codigo);
                carritoAPagar.orElse(null).setPagado(pagado);
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public Carrito agregarArticulo(int codigo, int nuevaCantidadDeArtiulos){
        try {
            if (carritoRepository.existsById(codigo)){
                Optional<Carrito>carritoAModificar = carritoRepository.findById(codigo);
                carritoAModificar.orElse(null).setCantidadDeArticulos(nuevaCantidadDeArtiulos);
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public boolean borrarCarrito(int codigo){
        try {
            carritoRepository.deleteById(codigo);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
