package org.acme.model;
import jakarta.persistence.Entity;

//import jakarta.ws.rs.FormParam;
//import org.jboss.resteasy.annotations.providers.multipart.PartType;

@Entity
public class Dados extends DefaltEntity {
    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
