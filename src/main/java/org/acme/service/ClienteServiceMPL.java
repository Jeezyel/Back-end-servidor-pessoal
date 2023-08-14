package org.acme.service;

import java.util.List;
import java.util.Set;

import org.acme.dto.ClienteDTO;
import org.acme.dto.ClienteResponseDTO;
import org.acme.repository.ClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@ApplicationScoped
public class ClienteServiceMPL implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    Validator validator;






    

    @Override
    public Boolean alterarSenha(String login, String senhaAtual, String novaSenha) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarSenha'");
    }

    @Override
    public List<ClienteResponseDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public ClienteResponseDTO create(ClienteDTO clienteDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ClienteResponseDTO update(String login, ClienteDTO clienteDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ClienteResponseDTO get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    private void validar(ClienteDTO clienteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);

      //  LOG.debug("verificando se ja foi criado ");
        if (!violations.isEmpty()){

          //  LOG.debug("retornando uma Exception ");
            throw new ConstraintViolationException(violations);
        }


    }
    
}
