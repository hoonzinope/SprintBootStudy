package com.weekend.cafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpSession;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .cors().disable()
                .authorizeRequests(request -> request
                        //.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .antMatchers("/status","/resources/**","/error","/duplicated-login").permitAll()
//                        .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
//                        .loginPage("/login")
                        .loginProcessingUrl("/login_process")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                        .permitAll())
                .sessionManagement()
                .sessionFixation()
                .changeSessionId()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
//                .expiredUrl("/duplicated-login")
                .sessionRegistry(sessionRegistry());

        httpSecurity
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    if (session != null) {
                        sessionRegistry().removeSessionInformation(session.getId());
                        session.invalidate();
                    }
                }))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "remember-me");

        httpSecurity
                .headers().frameOptions().disable();
        return httpSecurity.build();
    }

}
