package br.com.bv.juliocesar.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bv.juliocesar.api.mappers.MensagemMapper;

@Path("mensagem")
public class MensagemResource {
	
	@Autowired
	MensagemMapper mensagemMapper;
	
	String teste = "";
	
	@GET
	@Path("page")
	public String getClientes() { 
		return "mensagem";
	}

	@POST
	@Path("load")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAtividades(@Context UriInfo uriInfo) {

		String testea = "";
		
		return Response.ok(mensagemMapper.loadMensagem()).build();
	}
	
}
