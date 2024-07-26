package org.acme.service;

import org.acme.model.Dados;

import java.io.File;
import java.util.List;

public interface FileService {

    List<Dados> getAll();



    void salvarImagem( String nomeImagem, byte[] imagem);

    void salvarVideo( String videoNome, byte[] video);

    File download(String nomeArquivo);

    List<Dados> findByType(String type);

    List<Dados> findByName(String name);

    void DeleteByName(String name);

}
