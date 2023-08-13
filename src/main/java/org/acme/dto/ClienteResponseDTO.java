package org.acme.dto;

import java.util.Set;

import org.acme.model.Cliente;
import org.acme.model.Perfil;
import org.acme.model.Telefone;

public record ClienteResponseDTO (
    long id,
    String nome,
    String login,
    String senha,
    String cpf,
    Telefone telefone,
    Set<Perfil> perfil
) {
    public ClienteResponseDTO(Cliente cliente){
        this(
        cliente.getId(),    
        cliente.getNome(),
        cliente.getLogin(),
        cliente.getSenha(),
        cliente.getCpf(),
        cliente.getTelefone(),
        cliente.getPerfis()
        );
    }
}
