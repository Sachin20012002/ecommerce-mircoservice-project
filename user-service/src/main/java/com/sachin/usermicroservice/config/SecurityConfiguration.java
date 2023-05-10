package com.sachin.usermicroservice.config;

import com.sachin.usermicroservice.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST ={
                   "/ats",
                    "/login",
                    "/register",
                    "/users/password/**",
                    "/users/reset-password-mail",
                    "/users/token-validation/**",
    };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

   /*   CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
      requestHandler.setCsrfRequestAttributeName("_csrf");
    */


      http.cors().
            configurationSource(request -> {
              CorsConfiguration config = new CorsConfiguration();
              config.addAllowedOriginPattern("*");
              //config.setAllowedOrigins(Collections.singletonList("http://192.168.1.34:3000"));
              config.setAllowedMethods(Collections.singletonList("*"));
              config.setAllowCredentials(true);
              config.setAllowedHeaders(Collections.singletonList("*"));
              config.setExposedHeaders(List.of("Authorization"));
              config.setMaxAge(3600L);
              return config;
            }
            ).and()

//            .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact","/register")
//              .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//              .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

              .csrf().disable()
            .authorizeHttpRequests()
           // .antMatchers(AUTH_WHITELIST)
              .antMatchers("/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            //  .oauth2Login().and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}

/**
  CORS - cross-origin resource sharing
       It is a protection provided in modern day browsers, client sends a flight request to server,
       to know whether the server is ready to accept request from client origin.
  CSRF - cross-site resource forgery
       It is enabled by default in spring security, it forbids the put and post request.
 */
