package com.coderhouse.proyectofinal.service.payment;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.repository.DebitoRepository;
import com.coderhouse.proyectofinal.service.user.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebitoService {

    @Autowired
    private DebitoRepository tDebitoRepository;

    //Instanciando el carrito service para usar en el pago
    @Autowired
    CarritoService carritoService;

    public List<Debito> listarTarjetas(){
        return tDebitoRepository.findAll();
    }

    public Debito guardarTarjetaDeDebito(Debito debito){
        return tDebitoRepository.save(debito);
    }

    public boolean pagar(int nroTarjeta, int idCarrito){



            Optional<Debito>tarjeta = tDebitoRepository.findById(nroTarjeta);

            Carrito carrito = carritoService.buscarCarritoPorId(idCarrito);

            boolean pagado = tarjeta.orElse(null).pagar(carrito.getTotal());
            carritoService.pagarCarrito(idCarrito);

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
