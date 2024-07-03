package org.acme.model;
import jakarta.persistence.Entity;

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
