package acorn.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/auth/login", "/register").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/auth/login")
                    .defaultSuccessUrl("/main", true)
                    .failureUrl("/auth/login?error=true")
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/auth/login")
                    .permitAll()
            );

        return http.build();
    }
}
