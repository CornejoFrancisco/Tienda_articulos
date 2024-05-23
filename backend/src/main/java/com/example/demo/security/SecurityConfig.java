package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .exceptionHandling() //Permitimos el manejo de excepciones
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //Nos establece un punto de entrada personalizado de autenticación para el manejo de autenticaciones no autorizadas
                .and()
                .sessionManagement() //Permite la gestión de sessiones
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() //Toda petición http debe ser autorizada
                .requestMatchers(HttpMethod.POST, "/articulo").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/articulo/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/categoria/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/cliente/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/detalleFactura/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/factura/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/forma_pago/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/sucursal/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/unidad_medida/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/usuario/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}

