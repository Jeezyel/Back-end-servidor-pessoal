package org.acme.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import org.acme.model.Cliente;
import org.acme.model.Dados;
import org.acme.repository.ClienteRepository;
import org.acme.repository.DadosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


import org.acme.validation.ValidationException;


@ApplicationScoped
public class DadosFileServiceMPL implements FileService{

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "file" + File.separator;

    @Inject
    ClienteRepository clienteRepository;

    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem) {
        Cliente cliente = clienteRepository.findById(id);

        try {
            salvarImagem(imagem, nomeImagem);
            cliente.setNome(nomeImagem);
            // excluir a imagem antiga (trabalho pra quem????)
        } catch (IOException e) {
            throw new ValidationException("imagem", e.toString());
        }
    }
    private void salvarImagem(byte[] imagem, String nomeImagem) throws IOException {

        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
//        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
//        if (!listMimeType.contains(mimeType)) {
//            throw new IOException("Tipo de imagem não suportada.");
//        }

        // verificando o tamanho do arquivo - nao permitir maior que 10 megas
//        if (imagem.length > (1024 * 1024 * 10))
//            throw new IOException("Arquivo muito grande.");

        // criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando o nome do arquivo
//        String nomeArquivo = UUID.randomUUID()
//                +"."+mimeType.substring(mimeType.lastIndexOf("/")+1);

        String path = PATH_USER + nomeImagem;

        // salvando o arquivo
        File file = new File(path);
        // alunos (melhorar :)
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repedido.");

        // criando um arquivo no S.O.
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

//        return nomeImagem;

    }

    @Override
    public File download(String nomeArquivo) {
        File file = new File(PATH_USER+nomeArquivo);
        return file;
    }
}
