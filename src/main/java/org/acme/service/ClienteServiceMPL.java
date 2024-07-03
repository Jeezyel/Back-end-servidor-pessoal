package org.acme.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.acme.dto.ClienteDTO;
import org.acme.dto.ClienteResponseDTO;
import org.acme.model.Cliente;
import org.acme.repository.ClienteRepository;


import org.jboss.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@ApplicationScoped
public class ClienteServiceMPL implements ClienteService {

    
    private static final Logger LOG = Logger.getLogger(ClienteServiceMPL.class);

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    

    @Override
    public Boolean alterarSenha(String login, String senhaAtual, String novaSenha) {
        Cliente cliente = clienteRepository.findByLogin(login);

        if (cliente.getSenha() == hashService.getHashSenha(senhaAtual)) {
            cliente.setSenha(hashService.getHashSenha(novaSenha));
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean alterarLogim(String loginAtual, String loginNova, String senha) {
        Cliente cliente = clienteRepository.findByLogin(loginAtual);

        if (cliente.getSenha() == hashService.getHashSenha(senha)) {
            cliente.setLogin(loginNova);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<ClienteResponseDTO> getAll() {
        LOG.info("pegando todos os cliente");
        List<Cliente> listaCliente = clienteRepository.listAll();
        LOG.info("convertendo para o modelo do DTO");
        return listaCliente.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO create(ClienteDTO clienteDTO)throws ConstraintViolationException {
        LOG.info("validando o DTO e criando um cliente vacio");
        validar(clienteDTO);

        Cliente cliente = new Cliente();
        LOG.info("colocando os dados do cliente");
        cliente.setCpf(clienteDTO.cpf());
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setLogin(clienteDTO.login());
        cliente.setSenha(hashService.getHashSenha(clienteDTO.senha()));
        cliente.setPerfis(clienteDTO.perfil());
        LOG.info("salvando no banco");
        clienteRepository.persist(cliente);
        LOG.info("convertendo para o modelo do DTO e retornando");
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO update(String login, ClienteDTO clienteDTO) throws ConstraintViolationException {
        LOG.info("validando e procurando no banco");
        validar(clienteDTO);
        Cliente cliente = clienteRepository.findByLogin(login);
        LOG.info("construindo o cliente");
        LOG.info("colocando os dados do cliente");
        cliente.setCpf(clienteDTO.cpf());
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setLogin(clienteDTO.login());
        cliente.setSenha(hashService.getHashSenha(clienteDTO.senha()));
        cliente.setPerfis(clienteDTO.perfil());
        LOG.info("salvando no banco");
        clienteRepository.persist(cliente);
   LOG.info("salvar o cliente");
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public void delete(String login) {
        LOG.info("procurnado o cliente no banco");
        Cliente cliente = clienteRepository.findByLogin(login);
        LOG.info("apagando do banco");
        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteResponseDTO get(String login) {
        LOG.info("procurando no banco");
        Cliente cliente = clienteRepository.findByLogin(login);
        LOG.info("criando um modelo no DTO e retornando");
        return new ClienteResponseDTO(cliente );
    }

    private void validar(ClienteDTO clienteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);

        LOG.info("verificando se ja foi criado ");
        if (!violations.isEmpty()){

            LOG.info("retornando uma Exception ");
            throw new ConstraintViolationException(violations);
        }


    }

    @Override
    public Cliente findByLoginAndSenha(String login, String senha)  throws NullPointerException{
        LOG.info("pegando o cliente pelo login e a senha");
       /* Cliente cliente = clienteRepository.findByLogin(login);
        if(cliente != null && cliente.getSenha() == senha ){
            return  cliente;
        }else{
            return null;
        } */
        return clienteRepository.findByLoginAndSenha(login, hashService.getHashSenha(senha));
        
    }
    @Override
    public long count(){
        return clienteRepository.count();
    }

    
    
}
