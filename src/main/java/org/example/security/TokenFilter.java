package org.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.UserEntity;
import org.example.repository.UserEntityRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final UserEntityRepository repository;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("REQUEST " + request.getRequestURI());

        String token = request.getHeader(AUTHORIZATION);
        if (token == null || token.isBlank()) {;
            filterChain.doFilter(request, response);
            return;
        }
        Optional<UserEntity> optionalUser = repository.findByToken(token);
        if (optionalUser.isEmpty()) {
            filterChain.doFilter(request,response);
        } else {
            UserEntity user = optionalUser.get();
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
            filterChain.doFilter(request, response);
        }
    }
}