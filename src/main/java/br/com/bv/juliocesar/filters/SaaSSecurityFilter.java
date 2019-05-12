package br.com.bv.juliocesar.filters;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import br.com.bv.juliocesar.spring.TenantContextHolder;
import br.com.bv.juliocesar.spring.TenantId;
import br.com.bv.juliocesar.util.ApiUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class SaaSSecurityFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		configTenant(request, response, filterChain);	
		handleApi(request, response, filterChain);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private void configTenant(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//		http://localhost:8080/juliocesarSpring3/webapi/development/service/mensagem/page
		final String requestURL = ((HttpServletRequest) request).getRequestURL().toString() ;
		final String host = new URL( requestURL ).getHost();
		
		String subdomain = "";
		
		if ( requestURL.indexOf("/service") > 0 ) {			
			subdomain = requestURL.substring( 0, requestURL.indexOf("/service") );
			
			if ( (requestURL.indexOf("/webapi/") > 0) && (subdomain.length() > (requestURL.indexOf("/webapi/")+8)) )
				subdomain = subdomain.substring( (requestURL.indexOf("/webapi/")+8), subdomain.length() ).toUpperCase();
			else
				subdomain = "DEVELOPMENT";
		} else
			subdomain = "DEVELOPMENT";
				
		try {
			TenantContextHolder.clearTenantId();
			TenantContextHolder.setTenantId(TenantId.valueOf(subdomain));
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void handleApi(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		if(response.isCommitted())
			return;
		
		HttpServletResponse sresp =(HttpServletResponse) response;
		HttpServletRequest sreq = (HttpServletRequest) request;
		String uri = sreq.getRequestURI();
		
		if(uri.contains("/webapi")) {			
			String idCliente = ApiUtil.getIdCliente(TenantContextHolder.getTenantId());
			
			if(idCliente == null || (idCliente != null && !idCliente.equals(sreq.getHeader("idCliente")))) {
				sresp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Nao autorizado");
				return;
			}
				
			filterChain.doFilter(request, response);
		}
	}

}
