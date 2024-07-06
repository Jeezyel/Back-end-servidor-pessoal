package org.acme.resouce;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import org.acme.form.DadosImageForm;
import org.acme.form.VideoForm;
import org.acme.service.FileService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/file")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileResource {

    private static final Logger LOG = Logger.getLogger(FileResource.class);

    @Inject
    FileService fileService;
    @GET
    @Path("GetAllImage")
    public Response GetAll (){
        LOG.info("getALL");
        return Response.ok(fileService.getAll()).build();
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm DadosImageForm form) {
        LOG.info("nome imagem: "+form.getNomeFile());

        fileService.salvarImagem( form.getNomeFile(), form.getImagem());
        return Response.noContent().build();
    }


//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response updateVideo(@PathParam("id") String id, @MultipartForm VideoForm form) {
//        try {
//            // Caminho onde os vídeos são armazenados
//            Path videoPath = Paths.get("videos", id + ".mp4");
//
//            // Sobrescrever o vídeo existente
//            try (InputStream inputStream = form.getVideoData()) {                                   //analizar o que o chatgpt fez e melhorar para o seu projeto
//                Files.copy(inputStream, videoPath);
//            }
//
//            return Response.ok("Vídeo atualizado com sucesso.").build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o vídeo.").build();
//        }
//    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {

        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }
}
