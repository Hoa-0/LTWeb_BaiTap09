package vn.iotstar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.iotstar.service.UserDetailsServiceCustom;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Cho phép phân quyền bằng Annotation (@PreAuthorize)
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;

    // 1. Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. UserDetailsService (lấy User từ DB)
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsServiceCustom;
    }

    // 3. Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // 4. HTTP Security (Phân quyền URL + Login + Logout)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho demo

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/login", "/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/products/new", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginPage("/login") // Trang login custom
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/index", true) // Sau khi login thành công -> index.jsp
                        .failureUrl("/login?error=true")
                        .permitAll())

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout=true")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll())

                .exceptionHandling(ex -> ex.accessDeniedPage("/403")); // Trang 403.jsp
        return http.build();
    }
}
