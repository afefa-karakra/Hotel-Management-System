package com.example.hotelmanagementsystem.config;


import com.example.hotelmanagementsystem.Repository.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;

//  @Override
//  protected void doFilterInternal(
//      @NonNull HttpServletRequest request,
//      @NonNull HttpServletResponse response,
//      @NonNull FilterChain filterChain
//  ) throws ServletException, IOException {
//
//   // System.out.println("start filter function");
//   // System.out.println(request.getServletPath());
//
////    if (request.getServletPath().contains("/api/v1/auth")) {
////     // System.out.println("in if auth");
////      filterChain.doFilter(request, response);
////      return;
////    }
//
//   // System.out.println("after if auth");
//    final String authHeader = request.getHeader("Authorization");
//    System.out.println(authHeader);
//   // System.out.println(authHeader);
//    final String jwt;
//    final String userEmail;
//   // System.out.println("start authenticate");
//    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//      filterChain.doFilter(request, response);
//      return;
//    }
//    jwt = authHeader.substring(7);
//    System.out.println(jwt);
//
//    System.out.println(jwt);
//    userEmail = jwtService.extractUsername(jwt);
//
//    System.out.println(userEmail);
//
//    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//      System.out.println("authorities:");
//      System.out.println(userDetails.getAuthorities());
//      var isTokenValid = tokenRepository.findByToken(jwt)
//          .map(t -> !t.isExpired() && !t.isRevoked())
//          .orElse(false);
//      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//            userDetails,
//            null,
//            userDetails.getAuthorities()
//        );
//          authToken.setDetails(
//            new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//      }
//    }
//    filterChain.doFilter(request, response);
//  }
//}

  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authHeader.substring(7);

    try {
      String userEmail = jwtService.extractUsername(jwt);

      if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        var isTokenValid = tokenRepository.findByToken(jwt)
                .map(t -> !t.isExpired() && !t.isRevoked())
                .orElse(false);

        if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
          );
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    } catch (ExpiredJwtException ex) {
      // Handle expired token
      // For example, you can return an HTTP 401 Unauthorized response to the client
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("JWT Token Expired");
      return;
    }

    filterChain.doFilter(request, response);
  }
}