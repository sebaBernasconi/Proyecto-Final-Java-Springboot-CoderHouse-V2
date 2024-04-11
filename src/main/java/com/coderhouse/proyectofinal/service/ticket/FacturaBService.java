package com.coderhouse.proyectofinal.service.ticket;

import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.repository.FacturaBRepository;
import com.coderhouse.proyectofinal.service.user.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaBService {

    @Autowired
    private FacturaBRepository facturaBRepository;

    public List<FacturaB>listarFacturasB(){
        return facturaBRepository.findAll();
    }

    public FacturaB guardarFacuraB(FacturaB facturaB){
        return facturaBRepository.save(facturaB);
    }

   //No hay metodo eliminar porque la factura es un doc comercial. No puede ser borradi
}
