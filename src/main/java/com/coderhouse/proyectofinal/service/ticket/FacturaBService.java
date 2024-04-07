package com.coderhouse.proyectofinal.service.ticket;

import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.repository.FacturaBRepository;
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

  /* public List<FacturaB>listarFacturasBDeUnCliente(int cuil){
        return facturaBRepository.findAllByCuil(cuil);
    }*/

    public FacturaB guardarFacuraB(FacturaB facturaB){
        return facturaBRepository.save(facturaB);
    }

    public boolean eliminarFacutraB(int nroFacutra){
        try {
            facturaBRepository.deleteById(nroFacutra);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
