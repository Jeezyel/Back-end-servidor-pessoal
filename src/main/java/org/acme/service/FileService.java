package org.acme.service;

import org.acme.model.Dados;

import java.io.File;
import java.util.List;

public interface FileService {

    List<Dados> getAll();

    void salvarImagem( String nomeImagem, byte[] imagem);

    void salvarVideo( String nomeImagem, byte[] imagem);

    File download(String nomeArquivo);


}
