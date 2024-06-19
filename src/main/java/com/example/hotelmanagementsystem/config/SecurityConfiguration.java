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

  private static final String[] WHITE_LIST_URL = {
        //  "/api/v1/employee/**" ,
          "/api/v1/HousekeepingTasks/**",
          "/api/v1/billing/**",
       //   "/api/v2/room/**",
       //   "/api/room/**",
      //    "/api/rooms/**",
      //    "/api/v1/room"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                     auth.requestMatchers(WHITE_LIST_URL).permitAll()
                    .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers("/api/v1/employee/**").hasAuthority(ADMIN.name())
                 //    .requestMatchers("/api/v1/reservation").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/reservation/{id}").hasAnyAuthority(CUSTOMER.name() , ADMIN.name())
                     .requestMatchers(HttpMethod.GET, "/api/v1/reservation/name/{name}").hasAnyAuthority(CUSTOMER.name() , ADMIN.name())
                     .requestMatchers(HttpMethod.DELETE, "/api/v1/reservation/{id}").hasAuthority(ADMIN.name())
                     .requestMatchers(HttpMethod.POST, "/api/v1/reservation").hasAuthority(ADMIN.name())
                     .requestMatchers(HttpMethod.PUT, "/api/v1/reservation/{id}").hasAuthority(ADMIN.name())

                     .requestMatchers("/api/v1/room/**").hasAuthority(ADMIN.name())


                            .requestMatchers("/api/v1/customers/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/customers").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/customers/{id}").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/customers/{id}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/customers").permitAll()
                    .anyRequest().authenticated()

            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
