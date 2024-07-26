package org.acme.dto;

import org.acme.model.Cliente;
import org.acme.model.Perfil;
import org.acme.model.Telefone;

import java.util.Set;

public record ClienteResponseSimpleDTO(
    long id,
    String nome,
    Set<Perfil> perfil
) {
    public ClienteResponseSimpleDTO(Cliente cliente){
        this(
        cliente.getId(),    
        cliente.getNome(),
        cliente.getPerfis()
        );
    }
}
