package com.coderhouse.proyectofinal.service;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.repository.DebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitoService {

    @Autowired
    private DebitoRepository tDebitoRepository;

    public List<Debito> listarTarjetas(){
        return tDebitoRepository.findAll();
    }

    public Debito guardarTarjetaDeDebito(Debito debito){
        return tDebitoRepository.save(debito);
    }

    public Debito actualizarSaldo(int nroTarjeta, float nuevoSaldo, Debito debito){

        try{
            if (tDebitoRepository.existsById(nroTarjeta)){
                debito.setSaldo(nuevoSaldo);
                return tDebitoRepository.save(debito);
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }
        return null;
    }

    public boolean eliminarTarjedaDeDebito(int nroTarjeta){
        try {
            tDebitoRepository.deleteById(nroTarjeta);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
