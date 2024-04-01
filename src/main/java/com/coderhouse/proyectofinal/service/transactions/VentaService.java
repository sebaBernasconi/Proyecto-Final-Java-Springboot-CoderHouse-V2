package com.coderhouse.proyectofinal.service.transactions;

import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta>listarVentas(){
        return ventaRepository.findAll();
    }

    public Venta guardarVenta (Venta venta){
        return  ventaRepository.save(venta);
    }

    public List<Venta>VentasDeUnAdmin(int cuil){
        return ventaRepository.findAllByCuil(cuil);
    }

    public boolean eliminarVenta(int idVenta){
        try {
            ventaRepository.deleteById(idVenta);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
