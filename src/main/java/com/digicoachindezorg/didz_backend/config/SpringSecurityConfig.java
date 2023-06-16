package com.digicoachindezorg.didz_backend.config;

import com.digicoachindezorg.didz_backend.filter.JwtRequestFilter;
import com.digicoachindezorg.didz_backend.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    public final CustomUserDetailsService customUserDetailsService;
    public final PasswordEncoder passwordEncoder;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService , PasswordEncoder passwordEncoder, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }


    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()

                .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
                .requestMatchers(HttpMethod.GET, "/authenticated").authenticated()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/users/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/contactform").permitAll()
                .requestMatchers(HttpMethod.GET, "/contactform").permitAll() //Niet mogelijk om de requestMapping aan te passen.
                .requestMatchers(HttpMethod.GET, "/contactform/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/contactform/{id}").hasRole("ADMIN") //Niemand kan dit aanpassen.
                .requestMatchers(HttpMethod.DELETE, "/contactform/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/reviews").hasRole("USER")
                .requestMatchers(HttpMethod.PUT,"/reviews/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE,"/reviews/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/reviews/{id}").permitAll()
                .requestMatchers(HttpMethod.GET,"/reviews/product/{productId}").permitAll()
                .requestMatchers(HttpMethod.POST,"/invoices/new-user").permitAll()
                .requestMatchers(HttpMethod.POST,"/invoices/existing-user").hasRole("USER")
                .requestMatchers(HttpMethod.PUT,"/invoices/{invoiceId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/invoices/{invoiceId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/invoices/{invoiceId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/invoices/user/{userId}").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.POST,"/messages").hasAnyRole("USER", "COACH", "ADMIN")
                .requestMatchers(HttpMethod.PUT,"/messages/{id}").hasAnyRole("USER", "COACH", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/messages/{id}").hasAnyRole("USER", "COACH", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/messages/{id}").hasAnyRole("USER", "COACH", "ADMIN") //Extra logica bouwen voor user mag berichten van zichzelf openen, digicoach alleen studygroup, admin alles. (User ophalen en checken of het bericht met id in zijn berichtenbox zit. Zo niet een error, anders show message)
                .requestMatchers(HttpMethod.GET,"/messages/date/{date}").hasAnyRole("USER", "COACH", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/messages/users/{userId}").hasRole("ADMIN") //Mogelijk weg omdat de admin dit niet zomaar mag, dit kan ook in de database.
                .requestMatchers(HttpMethod.GET,"/messages/sent/{userId}").hasAnyRole("USER", "COACH", "ADMIN")
                .requestMatchers(HttpMethod.GET,"/messages/study-group/{studyGroupId}").hasAnyRole("USER", "COACH", "ADMIN")
                .requestMatchers(HttpMethod.POST,"/products").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/products/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/products/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/products/{id}").permitAll()
                .requestMatchers(HttpMethod.GET,"/products").permitAll()
                .requestMatchers(HttpMethod.POST, "/study-group").hasAnyRole("COACH","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/study-group/{id}").hasAnyRole("COACH","ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/study-group/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/study-group/{id}").hasAnyRole("USER", "COACH","ADMIN")
                .requestMatchers(HttpMethod.GET, "/study-group/by-product/{productId}").hasAnyRole( "COACH","ADMIN")
                .requestMatchers(HttpMethod.GET, "/study-group").hasRole("ADMIN") //Je kunt RequestMapping niet aanpassen.
                .requestMatchers(HttpMethod.POST, "/study-group/{studyGroupId}/users/{userId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/study-group/{studyGroupId}/users/{userId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/study-group/{studyGroupId}/users").hasAnyRole("USER","COACH","ADMIN")

                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}