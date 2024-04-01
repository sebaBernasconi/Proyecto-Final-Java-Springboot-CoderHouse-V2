package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.repository.FacturaCRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaCService {

    private FacturaCRepository facturaCRepository;

    public List<FacturaC>listarFacturasC(){
        return facturaCRepository.findAll();
    }

    public List<FacturaC>listarFacturasCDeUnCliente(int cuil){
        return facturaCRepository.findAllByCuil(cuil);
    }

    public FacturaC guardarFacutraC(FacturaC facturaC){
        return facturaCRepository.save(facturaC);
    }

    public boolean eliminarFacturaC(int nroFactura){
        try {
            facturaCRepository.deleteById(nroFactura);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
