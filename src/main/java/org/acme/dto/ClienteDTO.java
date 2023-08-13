package org.acme.dto;

import java.util.Set;

import org.acme.model.Perfil;
import org.acme.model.Telefone;

public record ClienteDTO(
    String nome,
    String login,
    String senha,
    String cpf,
    Telefone telefone,
    Set<Perfil> perfil

) {
    
}
