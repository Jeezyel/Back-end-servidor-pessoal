package org.acme.model;

import jakarta.persistence.Entity;


@Entity
public class Telefone extends DefaltEntity{
    
   private String codigoDeArea;
    private String numero;

    
    public String getCodigoDeArea() {
        return codigoDeArea;
    }
    public void setCodigoDeArea(String codigoDeArea) {
        this.codigoDeArea = codigoDeArea;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

}
