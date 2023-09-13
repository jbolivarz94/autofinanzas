package com.jbolivar.autofinanzas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class CustomSecurityWebFluxConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .pathMatchers("/usuario/**").authenticated()
                        .pathMatchers("/categoria/**").authenticated()
                        .pathMatchers("/transaccion/**").authenticated()
                        .pathMatchers("/cuenta/**").authenticated())
                        .httpBasic(Customizer.withDefaults())
                        .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return  serverHttpSecurity.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user, admin);
    }
}
