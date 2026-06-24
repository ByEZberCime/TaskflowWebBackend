package com.taskfloweb.fx.byezbercime.configuration;

import com.taskfloweb.fx.byezbercime.entity.Guess;
import com.taskfloweb.fx.byezbercime.entity.PrimaryOfficial;
import com.taskfloweb.fx.byezbercime.repositories.WebAdminRepositories;
import com.taskfloweb.fx.byezbercime.repositories.WebGuessRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private WebGuessRepositories guessRepositories;

    private WebAdminRepositories adminRepositories;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Şifreler veritabanında düz metin olarak tutuluyorsa:
        return new BCryptPasswordEncoder();
    }

    public WebSecurityConfiguration(WebGuessRepositories guessRepositories, WebAdminRepositories adminRepositories) {
        this.guessRepositories = guessRepositories;
        this.adminRepositories = adminRepositories;
    }

    @Bean
    public UserDetailsService officialUserDetailsService() {
        Collection<UserDetails> userInterface = new ArrayList<>();

        List<Guess> guests = guessRepositories.getAllTasks();
        List<PrimaryOfficial> officialsCertificate = adminRepositories.getOfficialsByCertificate();

        if (guests != null && !guests.isEmpty()) {
            if (officialsCertificate != null && !officialsCertificate.isEmpty()) {

                for (Guess responseGuess : guests) {
                    for (PrimaryOfficial officials : officialsCertificate) {

                        if (responseGuess.getCertificatedPrimaryOfficialCode().equals(officials.getCertificatedPrimaryOfficialCode())) {
                            if (responseGuess.getUsername().equals(officials.getPrimaryUsername())) {
                                if (responseGuess.getFirstName().equals(officials.getPrimaryFirstname())) {
                                    if (responseGuess.getSourName().equals(officials.getPrimarySourname())) {

                                        UserDetails userDetails = User
                                                .withUsername(officials.getPrimaryUsername())
                                                .password(passwordEncoder().encode(responseGuess.getPassword()))
                                                .roles(officials.getRole())
                                                .authorities(officials.getRole())
                                                .build();

                                        if (!userInterface.contains(userDetails)) {
                                            userInterface.add(userDetails);
                                        }

                                    }
                                }
                            }
                        }

                    }
                }

            }
        }


        return new InMemoryUserDetailsManager(new ArrayList<>(userInterface));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.
                        requestMatchers("/admin/certificate/**").hasAnyRole("admin").
                        requestMatchers("/acc/usrs/**").hasAnyRole("admin","user").

                        anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
