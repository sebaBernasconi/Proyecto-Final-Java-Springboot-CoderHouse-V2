package com.coderhouse.proyectofinal.service.user;

import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client>listarClientes(){
        return clientRepository.findAll();
    }

    public Client buscarClientePorCuil(int cuil){
        Optional<Client>cliente = clientRepository.findById(cuil);
        return cliente.orElse(null);
    }

    public Client guardarCliente(Client client){
        return clientRepository.save(client);
    }

    public Client modificarMail(int cuil, String nuevoMail){
        try {

            Optional<Client>clienteAModificar = clientRepository.findById(cuil);
            clienteAModificar.orElse(null).setMail(nuevoMail);
            return clientRepository.save(clienteAModificar.orElse(null));

        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Client modificarPassword(int cuil, String nuevaPassword){
        try {
            if (clientRepository.existsById(cuil)){
                Optional<Client>clienteAModificar = clientRepository.findById(cuil);
                clienteAModificar.orElse(null).setPassword(nuevaPassword);
            return clientRepository.save(clienteAModificar.orElse(null));
            }
        }catch (EmptyResultDataAccessException e){
            return null;
        }

        return null;
    }

    public List<Factura>listarFacturas(int cuil){
        try {
            Optional<Client>cliente = clientRepository.findById(cuil);

            List<Factura>facturasDelCliente = new ArrayList<>();

            for (Compra compra:
                 cliente.orElse(null).getCompras()) {
                facturasDelCliente.add(compra.getFactura());
            }

            return facturasDelCliente;
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean eliminarCliente(int cuil){
        try {
            clientRepository.deleteById(cuil);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
