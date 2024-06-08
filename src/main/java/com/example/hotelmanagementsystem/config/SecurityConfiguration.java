package com.example.hotelmanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.hotelmanagementsystem.user.Role.ADMIN;
import static com.example.hotelmanagementsystem.user.Role.CUSTOMER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                   // .requestMatchers("api/v1/employee").permitAll()
                 //   .requestMatchers(HttpMethod.GET, "/api/v1/products").permitAll()
                 //   .requestMatchers(HttpMethod.GET, "/api/v1/products/{id}").permitAll()
                //    .requestMatchers(HttpMethod.PUT, "/api/v1/employee/{id}").permitAll()
                     .requestMatchers("/api/v1/reservation").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/reservation/{id}").permitAll()
                     .requestMatchers(HttpMethod.DELETE, "/api/v1/reservation/{id}").permitAll()
                     .requestMatchers(HttpMethod.POST, "/api/v1/reservation").permitAll()
                     .requestMatchers(HttpMethod.PUT, "/api/v1/reservation/{id}").permitAll()
                     .requestMatchers("/api/v1/room").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/room").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/room/{id}").permitAll()
                     .requestMatchers(HttpMethod.DELETE, "/api/v1/room/{id}").permitAll()
                     .requestMatchers(HttpMethod.PUT, "/api/v1/room/{id}").permitAll()
                     .requestMatchers(HttpMethod.POST, "/api/v1/room/{id}").permitAll()
                     .requestMatchers("/api/v1/employee").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/employee/{id}").permitAll()
                      .requestMatchers("/api/v1/employee").permitAll()
                      .requestMatchers("/api/v1/employee/{id}").permitAll()
                      .requestMatchers(HttpMethod.DELETE, "/api/v1/employee/{id}").permitAll()
                      .requestMatchers(HttpMethod.PUT, "/api/v1/employee/{id}").permitAll()
                      .requestMatchers(HttpMethod.POST, "/api/v1/employee").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/customers").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/customers/{id}").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/customers/{id}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/customers").permitAll()
                //    .requestMatchers("/api/v1/employee").hasAuthority(CUSTOMER.name())
               //     .requestMatchers("/api/v1/employee/{id}").hasAuthority(CUSTOMER.name())
//                    .requestMatchers("/api/v1/products").hasAuthority(ADMIN.name())
                //    .requestMatchers("/api/v1/customers").hasAnyAuthority(CUSTOMER.name() , ADMIN.name())
                //    .requestMatchers("/api/v1/customers/{id}").hasAnyAuthority(ADMIN.name(), CUSTOMER.name())
//                    .requestMatchers("/api/v1/products/{productId}/stocks").hasAuthority(ADMIN.name())
//                    .requestMatchers("/api/v1/products/{productId}/stocks/{id}").hasAuthority(ADMIN.name())
//                    .requestMatchers("/api/v1/customers/{customerId}/orders").hasAnyAuthority(CUSTOMER.name(), ADMIN.name())
//                    .requestMatchers("/api/v1/customers/{customerId}/orders/{id}").hasAnyAuthority(CUSTOMER.name(), ADMIN.name())
//                    .requestMatchers("/api/v1/customers/{customerId}/orders/{orderId}/products").hasAnyAuthority(CUSTOMER.name(), ADMIN.name())
//                    .requestMatchers("/api/v1/customers/{customerId}/orders/{orderId}/products/{productId}").hasAnyAuthority(CUSTOMER.name(), ADMIN.name())
                    .anyRequest().authenticated()

            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
