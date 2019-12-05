package com.afip.afip.dominio;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import java.sql.Date;


@Entity

public class Impuesto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idimpuesto;

        @Column(name = "nombre")
        private String nombre;

        @Column(name = "fechaliquidacion")
        private Date fechaliquidacion;

        @Column(name = "porcentaje")
        private Double porcentaje;

        public Long getIdimpuesto() {
            return idimpuesto;
        }

    public Date getFechaliquidacion() {
        return fechaliquidacion;
    }

    public void setFechaliquidacion(Date fechaliquidacion) {
        this.fechaliquidacion = fechaliquidacion;
    }

    public void setIdimpuesto(Long idimpuesto) {
            this.idimpuesto = idimpuesto;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Double getPorcentaje() {
            return porcentaje;
        }

        public void setPorcentaje(Double porcentaje) {
            this.porcentaje = porcentaje;
        }

        @Override
        public String toString() {
            return "Impuesto [idimpuesto=" + idimpuesto + ", nombre=" + nombre + ", fechaliquidacion=" + fechaliquidacion+ ", porcentaje=" + porcentaje + "]";
        }



    }
