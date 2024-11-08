package com.artxp.artxp.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    //Filtro verifica permisos del token
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                auth -> {
                    //endpoints sin logueo
                    auth.requestMatchers("/api/auth/**").permitAll();
                    auth.requestMatchers("/h2-console/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/home/**", "/obra/**").permitAll();

                    //endpoint con autenticacion
                    // endopoint que requieren roles especificos

                    auth.requestMatchers(HttpMethod.POST, "/obra/**", "/artista/**", "/movimientoArtistico/**",
                            "/imagenes/**")
                            .hasAnyAuthority("ADMIN", "COLAB");

                    auth.requestMatchers(HttpMethod.PUT, "/obra/**", "/artista/**", "/movimientoArtistico/**",
                            "/imagenes/**", "/tecnicaObra/**")
                            .hasAnyAuthority("ADMIN", "COLAB");

                    auth.requestMatchers(HttpMethod.DELETE, "/obra/**", "/artista/**", "/movimientoArtistico/**",
                                    "/imagenes/**", "/tecnicaObra/**")
                            .hasAnyAuthority("ADMIN", "COLAB");

                    auth.requestMatchers("/usuario/**").hasAuthority("ADMIN"); //aun no se crea endpoint usuario
                    // endpoints que requieren autenticacion (al menos el rol de usuario)
                    auth.requestMatchers("/tecnicaObra/**").authenticated(); //ruta de ensayo
                    auth.anyRequest().authenticated();

                })

                .csrf(config -> config.disable())
                //Se elige tipo de sesión STATELESS en la que no recuerda nada entre cada request, no recuerda request anterior, ni va a recordar en la q estemos cuando termine
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Antes del filtro de jwt tiene q pasar el filtro de usernamePassAuth
                .authenticationProvider(authenticationProvider)
                .build();

    }
}
