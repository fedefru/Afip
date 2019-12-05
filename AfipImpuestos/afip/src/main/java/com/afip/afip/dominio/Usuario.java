package com.afip.afip.dominio;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clavefiscal;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    public Long getClavefiscal() {
        return clavefiscal;
    }

    public void setClavefiscal(Long clavefiscal) {
        this.clavefiscal = clavefiscal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Impuesto> getUsuimp() {
        return usuimp;
    }

    public void setUsuimp(List<Impuesto> usuimp) {
        this.usuimp = usuimp;
    }

    @Override
    public String toString() {
        return " clavefiscal=" + clavefiscal + ", nombre=" + nombre
                + ", apellido=" + apellido + "]";
    }


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "CLAVE_IMP")
    public List<Impuesto> usuimp;
}




