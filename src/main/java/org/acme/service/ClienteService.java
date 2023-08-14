package org.acme.service;

import java.util.List;

import org.acme.dto.ClienteDTO;
import org.acme.dto.ClienteResponseDTO;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ClienteService {

    
    Boolean alterarSenha(String login ,String senhaAtual , String novaSenha);
    
    List<ClienteResponseDTO> getAll();
    
    ClienteResponseDTO create(ClienteDTO clienteDTO);

    ClienteResponseDTO update(String login, ClienteDTO clienteDTO);
    
    void delete(Long id);

    ClienteResponseDTO get();

}
