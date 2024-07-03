package org.acme.dto;

import org.acme.model.Dados;

public record DadosResponseDTO(
        String nome
) {
    public DadosResponseDTO (Dados dados){
        this(dados.getNome());
    }
}
