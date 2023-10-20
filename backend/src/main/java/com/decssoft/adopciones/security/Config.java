package com.decssoft.adopciones.security;

import com.decssoft.adopciones.exceptions.CustomAccesDeniedHandler;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author mis_p
 */
@Configuration
@EnableWebSecurity
@Slf4j
@EnableMethodSecurity
public class Config {

    @Autowired
    DetailsUser detailsUser;

    @Bean
    public CustomAccesDeniedHandler customAccesDeniedHandler() {
        return new CustomAccesDeniedHandler();
    }

    @Bean
    public CustomAuthenticationEntryPoint entryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailsUser);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setExposedHeaders(Arrays.asList("*"));
        corsConfiguration.addAllowedOrigin("*");

        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain SecurityfilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests((t) -> {
            t.requestMatchers("/api/v1/authentication/login",
                    "/api/v1/animal/aniaml",
                    "/api/v1/animal/animales",
                    "/api/v1/protectora/list",
                    "/api/v1/protectora/protectora",
                    "/api/v1/protectora/create",
                    "/api/v1/transito/create").permitAll()
                    .requestMatchers("/api/v1/usuario/create",
                            "/api/v1/usuario/modificar").hasAnyRole("ENCARGADO", "ADMINISTRADOR")
                    .requestMatchers("/api/v1/protectora/encargado").hasAnyRole("ENCARGADO", "ADMINISTRADOR", "COLABORADOR")
                    .anyRequest().authenticated();
        }).authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(h -> {
            h.accessDeniedHandler(customAccesDeniedHandler());
            h.authenticationEntryPoint(entryPoint());
        });
        return http.build();
    }

}
