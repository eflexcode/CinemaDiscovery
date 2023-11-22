package com.larrex.AuthService.filter;

import com.larrex.AuthService.sevice.AuthService;
import com.larrex.AuthService.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtils;

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        String userEmail = null;
        String jwt = null;

        if (header != null && header.startsWith("Bearer ")){

            jwt = header.substring(7);

            try {
                userEmail = jwtUtils.getUsernameFromToken(jwt);
            }catch (IllegalArgumentException e){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Unable to get token");
            }catch (ExpiredJwtException jwtException){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Jwt token expired");
            }

        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails =  authService.userDetailsService().loadUserByUsername(userEmail);

            if (jwtUtils.validateToken(jwt,userDetails)){

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }


        }

filterChain.doFilter(request,response);
    }


}
