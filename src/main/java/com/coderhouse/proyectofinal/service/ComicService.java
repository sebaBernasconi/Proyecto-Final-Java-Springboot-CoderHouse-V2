package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean eliminarComic(int codigoDeProducto){
        try {
            comicRepository.deleteById(codigoDeProducto);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
