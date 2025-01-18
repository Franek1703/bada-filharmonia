package com.bada_project.filharmonia;

import com.bada_project.filharmonia.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.util.ArrayList;
import java.util.Map;
//import java.util.logging.Logger;

@Configuration
public class SecurityConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Using BCryptPasswordEncoder for better security
    }

    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http, AuthenticationProvider customAuthenticationProvider) throws Exception {
        http
                .securityMatcher("/admin/**") // Only secure admin endpoints
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/admin/login") // Admin login page
                        .defaultSuccessUrl("/admin", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .addLogoutHandler((request, response, auth) -> {
                            if (auth != null) {
                                SecurityContextHolder.clearContext();
                                request.getSession().invalidate();
                                System.out.println("User logged out and SecurityContext cleared.");
                            }                        })
                        .logoutSuccessUrl("/main")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .authenticationProvider(customAuthenticationProvider);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http, AuthenticationProvider customAuthenticationProvider) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/", "/logout", "/admin/logout", "/main","/login", "/admin/**", "/reservation/**", "/static/**", "/images/**", "/icons/**", "/css/**", "/js/**", "/reservation/event").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/user/login") // User login page
                                .successHandler((request, response, authentication) -> {
                                    SavedRequest savedRequest = (SavedRequest) request.getSession()
                                            .getAttribute("SPRING_SECURITY_SAVED_REQUEST");
                                    String redirectUrl = savedRequest != null
                                            ? savedRequest.getRedirectUrl()
                                            : request.getHeader("Referer");

                                        if(redirectUrl.contains("/user/login")){
                                            redirectUrl = redirectUrl.replace("/user/login", "");
                                        }
                                        if(redirectUrl.isEmpty()) {
                                            redirectUrl = "/main";
                                        }
                                    System.out.println("User logged in: " + authentication.getName());
                                    System.out.println("Redirecting to: " + redirectUrl);
                                    response.sendRedirect(redirectUrl);
                                })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .authenticationProvider(customAuthenticationProvider);
        return http.build();
    }

    @Bean
    public AuthenticationProvider customAuthenticationProvider(PasswordEncoder passwordEncoder) {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getName();
                String credentials = authentication.getCredentials().toString();

                // Admin Authentication Logic
                if ("admin".equals(username) && passwordEncoder.matches(credentials, passwordEncoder.encode("admin"))) {
                    UserDetails admin = User.withUsername(username)
                            .password(credentials)
                            .roles("ADMIN")
                            .build();
                    return new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
                }

                // Extract first name and last name (for simplicity)
                String[] parts = credentials.split(",");
                if (parts.length < 2) {
                    throw new AuthenticationException("Invalid credentials format. Expected 'FirstName,LastName'") {};
                }
                String firstName = parts[0];
                String lastName = parts[1];

                //TODO LOAD user from data base, if not exist create one and return user id

                int testId = 1;

                // Custom validation (this is where you can check against hardcoded values or future database)
                if ("504440436".equals(username) && "Franciszek".equals(firstName) && "Zarębski".equals(lastName)) {
                    UserDetails user = User.withUsername(username)
                            .password("") // No password required
                            .roles("USER")
                            .build();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new UserModel(firstName,lastName,testId,username)); // Add custom details
                    return auth;
                }
                throw new AuthenticationException("Invalid credentials") {};
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }
        };
    }
}
