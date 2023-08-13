package org.acme.repository;

import java.util.List;
import org.acme.model.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Cliente findByNome(String nome){
        if(nome == null){
            return null;
        }
        return (Cliente) find("nome",  nome  ).firstResult();
    }

    public Cliente findByLogin(String login){
        if(login == null){
            return null;
        }
        return find("login LIKE ?1", login).firstResult();
    }

    public List<Cliente> findByLoginList(String login){
        if(login == null){
            return null;
        }
        return find("login LIKE ?1", login).list();
    }
    
    public Cliente findByLoginAndSenha(String login, String senha) {

        if (login == null || senha == null)
            return null;

        return find("login = ?1 AND senha = ?2 ", login, senha).firstResult();
    }
}
