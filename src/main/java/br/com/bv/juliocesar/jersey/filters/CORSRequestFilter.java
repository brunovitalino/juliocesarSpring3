package br.com.bv.juliocesar.jersey.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSRequestFilter implements ContainerRequestFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		requestContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
		requestContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
		requestContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, accept, "
				+ "access-control-allow-credentials, access-control-allow-headers, access-control-allow-methods, "
				+ "access-control-allow-origin, access-control-max-age, content-type ");
		requestContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
		requestContext.getHeaders().putSingle("Access-Control-Max-Age", "1728000");
		
	}

}
