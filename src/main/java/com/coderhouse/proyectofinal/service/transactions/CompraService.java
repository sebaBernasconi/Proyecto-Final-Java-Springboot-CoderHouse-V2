package com.coderhouse.proyectofinal.service.transactions;

import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra>listarCompras(){
        return compraRepository.findAll();
    }

    public Compra buscarCompraPorId(int id){
        try {
            Optional<Compra> compra = compraRepository.findById(id);
            return compra.orElse(null);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public Compra guardarCompra(Compra compra){
        return compraRepository.save(compra);
    }

    /*public List<Compra>ComprasDeUnCliente(int cuil){
        return compraRepository.findAllByCuil(cuil);
    }*/

    public boolean eliminarCompra(int idCompra){
        try{
            compraRepository.deleteById(idCompra);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}

