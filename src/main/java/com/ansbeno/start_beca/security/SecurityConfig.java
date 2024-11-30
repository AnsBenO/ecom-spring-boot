package com.ansbeno.start_beca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ansbeno.start_beca.domain.user.Permission;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
class SecurityConfig {

      @Bean
      PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }

      @Bean
      SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))
                        .headers(headers -> headers
                                    .frameOptions(FrameOptionsConfig::sameOrigin));

            http.formLogin(form -> form
                        .loginPage("/login")

                        .defaultSuccessUrl("/")

                        .successHandler((HttpServletRequest request,
                                    HttpServletResponse response,
                                    Authentication authentication) -> {
                              request.getSession().setAttribute("loginSuccessMessage",
                                          "You have logged in successfully!");
                              response.sendRedirect("/");
                        })

                        .failureUrl("/login?error=true")

                        .permitAll())
                        .logout(logout -> logout.deleteCookies("remove")
                                    .invalidateHttpSession(true)
                                    .logoutUrl("/post-logout")
                                    .permitAll());

            http.authorizeHttpRequests(authConfig -> {
                  authConfig.requestMatchers(HttpMethod.GET, "/products")
                              .hasAnyAuthority(Permission.READ_ALL_PRODUCTS.name());
                  authConfig.requestMatchers("/products/add").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
                  authConfig.requestMatchers(HttpMethod.GET, "/categories")
                              .hasAnyAuthority(Permission.READ_ALL_PRODUCTS.name());
            });

            http.logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());
            http.authorizeHttpRequests(request -> request
                        .requestMatchers(
                                    "/",
                                    "/register",
                                    "/webjars/**",
                                    "/tailwind/**",
                                    "/register/**",
                                    "/login/**",
                                    "/css/**",
                                    "/js/**",
                                    "/assets/**",
                                    "/about",
                                    "/contact",
                                    "/home",
                                    "/h2-console/**")
                        .permitAll()
                        // All other requests need to be authenticated
                        .anyRequest().authenticated());

            return http.build();
      }

      @Bean
      AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
      }

}
