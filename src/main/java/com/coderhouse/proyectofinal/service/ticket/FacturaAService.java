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

    public FacturaA guardarFacturaA(FacturaA facturaA){
        return facturaARepository.save(facturaA);
    }

    //No hay metodo eliminar porque la factura es un doc comercial. No puede ser borradi
}
