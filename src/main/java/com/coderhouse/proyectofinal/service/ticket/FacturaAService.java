package com.coderhouse.proyectofinal.service.ticket;

import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.repository.FacturaARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaAService {

    @Autowired
    private FacturaARepository facturaARepository;

    public List<FacturaA> listarFacturasA(){
        return facturaARepository.findAll();
    }

    public List<FacturaA>listarFacturasADeUnCliente(int cuil){
        //Probar, capaz no ande
        return facturaARepository.findAllByCuil(cuil);
    }

    public FacturaA guardarFacturaA(FacturaA facturaA){
        return facturaARepository.save(facturaA);
    }

    public boolean eliminarFacturaA(int nroFactura){
        try{
            facturaARepository.deleteById(nroFactura);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
