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

import java.io.IOException;
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


    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {

        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

    @PATCH
    @Path("/video/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarVideo(@MultipartForm VideoForm form) {
        try {
            LOG.info("Nome do vídeo: " + form.getNomeFile());

            // Converter InputStream para byte[]
            byte[] videoBytes = form.getVideo().readAllBytes();

            fileService.salvarVideo(form.getNomeFile() , videoBytes);
            return Response.noContent().build();
        } catch (IOException e) {
            LOG.error("Erro ao salvar o vídeo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao salvar o vídeo.").build();
        }
    }

    @GET
    @Path("GetAllType/{tipoDoArquivo}")
    public Response findByType (@PathParam("tipoDoArquivo") String tipoDoArquivo){
        LOG.info("GetAllType");
        return Response.ok(fileService.findByType(tipoDoArquivo)).build();
    }

    @GET
    @Path("findByName/{name}")
    public Response findByName (@PathParam("name") String name){
        LOG.info("findByName");
        return Response.ok(fileService.findByName(name)).build();
    }

    @DELETE
    @Path("deletebyname/{name}")
    public void DeleteByName(@PathParam("name") String name){
        LOG.info("deleteByName");
        fileService.DeleteByName(name);
    }


}
