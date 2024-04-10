package com.coderhouse.proyectofinal.service.payment;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.repository.DebitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean pagar(int nroTarjeta, float total){

            Optional<Debito>tarjeta = tDebitoRepository.findById(nroTarjeta);

            boolean pagado = tarjeta.orElse(null).pagar(total);

            tDebitoRepository.save(tarjeta.orElse(null));

            return pagado;
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
