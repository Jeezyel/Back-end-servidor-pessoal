package org.acme.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import org.acme.model.Cliente;
import org.acme.model.Dados;
import org.acme.repository.DadosRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


import org.acme.validation.ValidationException;
import org.hibernate.service.NullServiceException;


@ApplicationScoped
public class DadosFileServiceMPL implements FileService{

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "file" + File.separator;

    @Inject
    DadosRepository dadosRepository;

    @Override
    public List<Dados> getAll()  {

        try {
            List<Dados> listaDados = dadosRepository.findAll().list();
            return listaDados;
        }catch (Exception e){

            return null;
        }
    }

    @Override
    @Transactional
    public void salvarImagem( String nomeImagem, byte[] imagem) {



        try {
            salvarImagem(imagem, nomeImagem);

            saveToDatabase(nomeImagem);

            // excluir a imagem antiga (trabalho pra quem????)
        } catch (IOException e) {
            throw new ValidationException("imagem", e.toString());
        }
    }

    @Override
    @Transactional
    public void salvarVideo( String nomeVideos, byte[] videos) {



        try {
            salvarVideo(videos, nomeVideos);

            saveToDatabase(nomeVideos);

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

    public void salvarVideo(byte[] video, String nomeVideo) throws IOException {
        // Verificando o tipo do vídeo
        String mimeType = Files.probeContentType(new File(nomeVideo).toPath());
        List<String> listMimeType = Arrays.asList("video/mp4", "video/avi", "video/mkv", "video/mov");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de vídeo não suportado.");
        }



        // Criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }


        String path = PATH_USER + nomeVideo;

        // Salvando o arquivo
        File file = new File(path);
        if (file.exists()) {
            throw new IOException("O nome gerado do vídeo está repetido.");
        }

        // Criando um arquivo no S.O.
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(video);
        // Garantindo o envio do último lote de bytes
        fos.flush();
        fos.close();
    }

    @Override
    public File download(String nomeArquivo) {
        File file = new File(PATH_USER+nomeArquivo);
        return file;
    }

    private void saveToDatabase(String nomeImagem) throws IOException{
        Dados dados = new Dados();

        dados.setNome(nomeImagem);

        dadosRepository.persist(dados);
    }

    @Override
    public List<Dados> findByType(String type) {

        try {
            return dadosRepository.findByType(type).list();
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public List<Dados> findByName(String name) {
        try {
            return dadosRepository.findByNome(name).list();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void DeleteByName(String name) {
        try {
            dadosRepository.delete(dadosRepository.findByNome(name).firstResult());
        }catch (Exception e){
            throw new ValidationException("delete ", e.toString());
        }
    }
}
