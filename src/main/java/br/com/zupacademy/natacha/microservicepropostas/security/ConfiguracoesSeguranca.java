package br.com.zupacademy.natacha.microservicepropostas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests(authorizeRequests ->
                    authorizeRequests
                            .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                            .antMatchers(HttpMethod.GET,"/propostas/**").hasAuthority("SCOPE_escopo-proposta")
                            .antMatchers(HttpMethod.POST,"/propostas/**").hasAuthority("SCOPE_escopo-proposta")
                            .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_escopo-proposta")
                            .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_escopo-proposta")
                            .antMatchers(HttpMethod.POST, "/viagens/**").hasAuthority("SCOPE_escopo-proposta")
//                            .antMatchers(HttpMethod.POST, "/actuator/**").hasAuthority("SCOPE_escopo-proposta")
                            .anyRequest().authenticated()
            ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        }
    }

