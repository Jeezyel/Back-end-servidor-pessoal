package org.acme.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.acme.model.Perfil;
import org.acme.model.Telefone;

public record ClienteDTO(
        @NotBlank(message = "o nome deve ser informado")
        String nome,
        @NotBlank(message = "o login deve ser imformado")
        String login,
        @NotBlank(message = "o senha deve ser imformado")
        String senha,
        @NotBlank(message = "o cpf deve ser imformado")
        String cpf,

        Telefone telefone,
        Set<Perfil> perfil

) {
    
}
