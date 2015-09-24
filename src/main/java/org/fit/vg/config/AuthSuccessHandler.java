package org.fit.vg.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
/**
 * authentication handler after a user has logged in 
 * @author Ravi Kumar Singh
 *
 */
public class AuthSuccessHandler implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse resp, Authentication arg2) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		final String targetUrl = "/drawGraphiti";	
        redirectStrategy.sendRedirect(req, resp, targetUrl);		
	}
	
	

}
