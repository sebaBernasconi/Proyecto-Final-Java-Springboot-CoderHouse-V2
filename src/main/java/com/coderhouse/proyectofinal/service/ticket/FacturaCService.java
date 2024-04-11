package com.coderhouse.proyectofinal.service.ticket;

import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.repository.FacturaCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaCService {

    @Autowired
    private FacturaCRepository facturaCRepository;

    public List<FacturaC>listarFacturasC(){
        return facturaCRepository.findAll();
    }

    public FacturaC guardarFacutraC(FacturaC facturaC){
        return facturaCRepository.save(facturaC);
    }

    //No hay metodo eliminar porque la factura es un doc comercial. No puede ser borradi
}
