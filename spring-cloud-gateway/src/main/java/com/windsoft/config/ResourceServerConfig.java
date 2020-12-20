package com.windsoft.config;

import com.windsoft.filter.AuthorizationManager;
import com.windsoft.handler.CustomServerAccessDeniedHandler;
import com.windsoft.handler.CustomServerAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.annotation.Resource;

/**
 * 资源服务器配置
 */
@Configuration
// 注解需要使用@EnableWebFluxSecurity而非@EnableWebSecurity,因为SpringCloud Gateway基于WebFlux
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Resource
    private AuthorizationManager authorizationManager;
    @Resource
    private CustomServerAccessDeniedHandler customServerAccessDeniedHandler;
    @Resource
    private CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/echo/hi").permitAll()
                .anyExchange().access(authorizationManager)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customServerAccessDeniedHandler) // 处理未授权
                .authenticationEntryPoint(customServerAuthenticationEntryPoint) //处理未认证
                .and().csrf().disable();

        return http.build();
    }
}