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
import jakarta.ws.rs.ClientErrorException;

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
        LOG.debug("pegando todos os cliente");
        List<Cliente> listaCliente = clienteRepository.listAll();
        LOG.debug("convertendo para o modelo do DTO");
        return listaCliente.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO create(ClienteDTO clienteDTO)throws ConstraintViolationException {
        LOG.debug("validando o DTO e criando um cliente vacio");
        validar(clienteDTO);

        Cliente cliente = new Cliente();
        LOG.debug("colocando os dados do cliente");
        cliente.setCpf(clienteDTO.cpf());
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setLogin(clienteDTO.login());
        cliente.setSenha(hashService.getHashSenha(clienteDTO.senha()));
        cliente.setPerfis(clienteDTO.perfil());
        LOG.debug("salvando no banco");
        clienteRepository.persist(cliente);
        LOG.debug("convertendo para o modelo do DTO e retornando");
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public ClienteResponseDTO update(String login, ClienteDTO clienteDTO) throws ConstraintViolationException {
        LOG.debug("validando e procurando no banco");
        validar(clienteDTO);
        Cliente cliente = clienteRepository.findByLogin(login);
        LOG.debug("construindo o cliente");
        cliente.setCpf(clienteDTO.cpf());
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setPerfis(clienteDTO.perfil());
// ver se vai salvar os novos dados caso n salvar descomente a linha de baixo
   //     clienteRepository.persist(cliente);
   LOG.debug("salvar o cliente");
        return new ClienteResponseDTO(cliente);
    }

    @Override
    public void delete(String login) {
        LOG.debug("procurnado o cliente no banco");
        Cliente cliente = clienteRepository.findByLogin(login);
        LOG.debug("apagando do banco");
        clienteRepository.delete(cliente);
    }

    @Override
    public ClienteResponseDTO get(String login) {
        LOG.debug("procurando no banco");
        Cliente cliente = clienteRepository.findByLogin(login);
        LOG.debug("criando um modelo no DTO e retornando");
        return new ClienteResponseDTO(cliente );
    }

    private void validar(ClienteDTO clienteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);

        LOG.debug("verificando se ja foi criado ");
        if (!violations.isEmpty()){

            LOG.debug("retornando uma Exception ");
            throw new ConstraintViolationException(violations);
        }


    }

    @Override
    public Cliente findByLoginAndSenha(String login, String senha)  throws NullPointerException{
        LOG.debug("pegando o cliente pelo login e a senha");
       /* Cliente cliente = clienteRepository.findByLogin(login);
        if(cliente != null && cliente.getSenha() == senha ){
            return  cliente;
        }else{
            return null;
        } */
        Cliente cliente = clienteRepository.findByLoginAndSenha(login, hashService.getHashSenha(senha));

        return clienteRepository.findByLoginAndSenha(login, hashService.getHashSenha(senha));
        

        
    }

    
    
}
