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

    public Comic buscarComicPorCodigo(int codigo){
        try {
            Optional<Comic> comic =  comicRepository.findByCodigoDeProducto(codigo);
            return comic.orElse(null);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Comic guardarComic(Comic comic){
        return comicRepository.save(comic);
    }

    public Comic editarComic(int codigo, Comic comic){
        try {
                Optional<Comic> comicActualizado = comicRepository.findByCodigoDeProducto(codigo);
                comicActualizado.orElse(null).editarComic(comic.getNombre(), comic.getDescripcion(),
                        comic.getAutor(),comic.getIdioma(),comic.isTapaDura());

                return comicRepository.save(comicActualizado.orElse(null));

        }catch (EmptyResultDataAccessException e){
            return null;
        }
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
            Optional<Comic> comic = comicRepository.findByCodigoDeProducto(codigoDeProducto);
            comic.orElse(null).modificarPrecio(nuevoPrecio);
            return comicRepository.save(comic.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public boolean eliminarComic(int codigoDeProducto){
        try {
            Optional<Comic> comic = comicRepository.findByCodigoDeProducto(codigoDeProducto);
            comicRepository.deleteById(comic.orElse(null).getIdProd());
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
