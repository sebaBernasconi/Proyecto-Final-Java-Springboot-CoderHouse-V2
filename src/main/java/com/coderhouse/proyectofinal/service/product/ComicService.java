package com.coderhouse.proyectofinal.service.product;

import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComicService {

    @Autowired
    private ComicRepository comicRepository;

    public List<Comic>listarComics(){
        return comicRepository.findAll();
    }

    public Comic guardarComic(Comic comic){
        return comicRepository.save(comic);
    }

    public Comic editarComic(int codigo,String nuevoNombre, String nuevaDesc, float nuevoPrecio,
                             String nuevoAutor, String nuevoIdioma, Comic comic){
        try {
            if (comicRepository.existsById(codigo)){
                comic.setNombre(nuevoNombre);
                comic.setDescripcion(nuevaDesc);
                comic.setPrecio(nuevoPrecio);
                comic.setAutor(nuevoAutor);
                comic.setIdioma(nuevoIdioma);
                return comicRepository.save(comic);
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public Comic actualizarStockPostVenta(int codigoDeProducto){
        try {
            Optional<Comic> comicParaActualizar = comicRepository.findById(codigoDeProducto);
            comicParaActualizar.orElse(null).actualizarStock();
            return comicRepository.save(comicParaActualizar.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Comic modificarPrecio(int codigoDeProducto, float nuevoPrecio){
        try {
            Optional<Comic> comic = comicRepository.findById(codigoDeProducto);
            comic.orElse(null).modificarPrecio(nuevoPrecio);
            return comicRepository.save(comic.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public boolean eliminarComic(int codigoDeProducto){
        try {
            comicRepository.deleteById(codigoDeProducto);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
