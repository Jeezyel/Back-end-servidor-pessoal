package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Dados;

@ApplicationScoped
public class DadosRepository implements PanacheRepository<Dados> {
    public PanacheQuery<Dados> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public PanacheQuery<Dados> findByType(String type) {
        if (type == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + type.toLowerCase());
    }
}
