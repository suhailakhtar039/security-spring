package com.sec1.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * Always remember to choose {noop} if you want to provide password as it is. For example in my
     * scenario, I want to pass password as 12345
     */
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withUsername("user")
                .password("{noop}User@786$^yr")
                .authorities("read")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password("{bcrypt}$2a$12$zeg.jTTBKjRZEvXFlR1MPOuBqJD37BGmSNXTF2WhoQiBFdDic45Cm")
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * To check if a password encoder is breached
     */
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
