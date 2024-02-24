package com.blog.security;

import com.blog.exception.BlogAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get JWT from http request
        String token = getJWTfromRequest(request);
        try {
            if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
                // get username from token
                String username = tokenProvider.getUsernameFromJWT(token);
                // load user associated with token
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);//This method is responsible for fetching user details, such as the user's password and authorities (roles), from a data source (e.g., a database) based on the provided username.
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());// userDetails.getAuthorities()-: The authorities (roles) assigned to the user.
                //The WebAuthenticationDetails class typically holds details about the web-specific aspects of an authentication request, such as the remote address (IP address of the client) and session ID.
                //it include  the remote address(IP address of the client) and session ID,
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            // Handle the exception appropriately, log or throw a new exception
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        } catch (BlogAPIException e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request, response);
    }

    // Bearer <accessToken>
    private String getJWTfromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
