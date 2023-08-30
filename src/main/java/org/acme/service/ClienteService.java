package org.acme.service;

import java.util.List;

import org.acme.dto.ClienteDTO;
import org.acme.dto.ClienteResponseDTO;
import org.acme.model.Cliente;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ClienteService {

    
    Boolean alterarSenha(String login ,String senhaAtual , String novaSenha);

    Boolean alterarLogim(String loginAtual, String loginNova ,String senha);
    
    List<ClienteResponseDTO> getAll();
    
    ClienteResponseDTO create(ClienteDTO clienteDTO);

    ClienteResponseDTO update(String login, ClienteDTO clienteDTO);
    
    void delete(String login);

    ClienteResponseDTO get(String login);

    Cliente findByLoginAndSenha(String login, String senha);

}
