package com.coderhouse.proyectofinal.service.user;

import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.repository.AdminRepository;
import jakarta.persistence.Access;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin>listarAdmins(){
        return adminRepository.findAll();
    }

    public Admin guardarAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin modificarMail(int cuil, String nuevoMail){
        try {
            Optional<Admin>adminAModificar = adminRepository.findById(cuil);
            adminAModificar.orElse(null).setMail(nuevoMail);
            return adminRepository.save(adminAModificar.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Admin modificarPassword(int cuil,String nuevaPassword){
        try {
            Optional<Admin>adminAModificar = adminRepository.findById(cuil);
            adminAModificar.orElse(null).setPassword(nuevaPassword);
            return adminRepository.save(adminAModificar.orElse(null));
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public boolean eliminarAdmin(int cuil){
        try {
           adminRepository.deleteById(cuil);
           return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
