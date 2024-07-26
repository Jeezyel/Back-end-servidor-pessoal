package org.acme.resouce;

import java.util.List;

import jakarta.ws.rs.core.Response;
import org.acme.dto.ClienteDTO;
import org.acme.service.ClienteService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import jakarta.ws.rs.core.MediaType;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {
    
    
    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @Inject
    ClienteService clienteservice;

//    @Inject
//    JsonWebToken tokenJwt;


    @GET
    @Path("/getAll")
   // @RolesAllowed({"Admin"})
    public Response getAll(){
        LOG.info("buscnado todos os clientes");
        return Response.ok(clienteservice.getAll()).build();
    }


    // create
    @POST
    @Path("/insert")
  //  @RolesAllowed({"Admin"})
    @Transactional
    public Response insert(ClienteDTO clienteDTO) {
        LOG.info("criando clientes");
        return Response.ok(clienteservice.create(clienteDTO)).build();
    }
    // update
    @POST
    @Path("/update/{login}")
   // @RolesAllowed({"Admin","User"})
    @Transactional
    public Response update(@PathParam("login") String login, ClienteDTO clienteDTO) {

        LOG.info("atualizando o clientes selecionado pelo id");
        
        return Response.ok(clienteservice.update(login , clienteDTO)).build();
    }

  /*   @POST
    @Path("/cadastor")
  //  @RolesAllowed({"Admin","User" , "Cliente"})
    @Transactional
    public ClienteResponseDTO cadastra(ClienteDTO clienteDTO) {

        LOG.info("atualizando o clientes selecionado pelo id");
        
        return clienteservice.create( clienteDTO);
    }*/


    @DELETE
    @Path("/delete/{login}")
 //   @RolesAllowed({"Admin"})
    public void Delete(@PathParam("login") String login){

        LOG.info("selecionado o cliente e apagando o cadastro");
        clienteservice.delete(login);
    }

    @GET
    @Path("/count")
  //  @RolesAllowed({"Admin"})
    public long count(){
        LOG.info("count");
        return clienteservice.count();
    }

    @GET
    @Path("/searchForLogin/{login}")
  //  @RolesAllowed({"Admin","User"})
    public Response searchForLogin(@PathParam("login") String login){

        LOG.info("procurando por login do cliente");
        return Response.ok(clienteservice.get(login)).build();
    }
}
