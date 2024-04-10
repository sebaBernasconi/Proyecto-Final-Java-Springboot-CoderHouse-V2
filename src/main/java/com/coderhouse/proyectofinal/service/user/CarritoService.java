package com.coderhouse.proyectofinal.service.user;

import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.repository.CarritoRepository;
import com.coderhouse.proyectofinal.service.product.ComicService;
import com.coderhouse.proyectofinal.service.product.FiguraDeAccionService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito>listarCarritos(){
        return carritoRepository.findAll();
    }

    public Carrito guardarCarrito(Carrito carrito){
        return carritoRepository.save(carrito);
    }

    public Carrito pagarCarrito(int codigo){
        try {
            if (carritoRepository.existsById(codigo)){ //preguntar si no hay drama en usar optional(?
                Optional<Carrito> carritoAPagar = carritoRepository.findById(codigo);
                carritoAPagar.orElse(null).setPagado(true);
                return carritoRepository.save(carritoAPagar.orElse(null));
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public Carrito agregarComic(int codigo, Comic comic){
        try {
            //Obteniendo el carrito
            Optional<Carrito>carritoAModificar = carritoRepository.findById(codigo);

            //Asignando el producto al carrito
            carritoAModificar.orElse(null).agregarAlCarrito(comic);

            return carritoRepository.save(carritoAModificar.orElse(null));

        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Carrito agregarFiguraDeAccion(int codigo, FiguraDeAccion figuraDeAccion){
        try {
            //Obteniendo el carrito
            Optional<Carrito>carritoAModificar = carritoRepository.findById(codigo);

            //Asignando el producto al carrito
            carritoAModificar.orElse(null).agregarAlCarrito(figuraDeAccion);

            return carritoRepository.save(carritoAModificar.orElse(null));

        }catch (EmptyResultDataAccessException e){
            return null;
        }
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
