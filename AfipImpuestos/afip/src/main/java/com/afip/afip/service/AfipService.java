package com.afip.afip.service;

import com.afip.afip.dominio.Impuesto;
import com.afip.afip.dominio.Usuario;
import com.afip.afip.repository.ImpuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AfipService {

    @Autowired
    ImpuestoRepository impuestoRepository;
    Impuesto impuesto;
    Usuario usuario;


    public Impuesto traerImpuesto(String nombre){
        return (Impuesto) impuestoRepository.findAllByNombreLike(nombre);
    }

}
