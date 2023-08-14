package org.acme.service;

import org.acme.model.Cliente;

public interface TokenJwtService {

    public String generateJwt(Cliente cliente);

    public String getLoginJtw(String loginCLiente);
    
}