package com.dutn.be_do_an_vat.sercurity;

import com.dutn.be_do_an_vat.entity.Role;
import com.dutn.be_do_an_vat.entity.base_entity.E_Role;
import com.dutn.be_do_an_vat.exception.AnthorizationException;
import com.dutn.be_do_an_vat.exception.CustomeAccessDeniedException;
import com.dutn.be_do_an_vat.repositoty.iRole;
import com.dutn.be_do_an_vat.utility.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.dutn.be_do_an_vat.repositoty")
public class SecurityConfig {

    private static List<Role> roleList;

    @Value("${project.endpont.v1}")
    private String apiPrefix;

    @Autowired
    private iRole roleService;

    @Autowired
    private CustomerUserDetailsServiceImpl userDetailsService;

    @Autowired
    private com.dutn.be_do_an_vat.sercurity.JwtTokenFilter jwtTokenFilter;

    @Autowired
    private CustomeAccessDeniedException customeAccessDeniedException;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Bean để dùng quản lý các người dùng đã đăng nhập
     *
     * @param config AuthenticationManager.class
     * @return AuthenticationManager object bean
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Cấu hình AuthenticationProvider trong Spring Security giúp xác thực người dùng bằng cách
     * sử dụng UserDetailsService và PasswordEncoder
     */
    @Bean
    protected AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean cho SecurityFilterChain
     * Sử dụng SecurityFilterChain cấu hình cho Spring Security
     * Bao gồm phân quyền các endpoint
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        roleList = roleService.findAll();
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> {
                    r.requestMatchers(HttpMethod.POST, apiPrefix + "/login").permitAll()
                            .requestMatchers(HttpMethod.POST, apiPrefix + "/taikhoan").permitAll();
                    roleList.forEach(role -> {
                        role.getPermisions().forEach(permission -> {
                            r.requestMatchers(HttpMethod.valueOf(permission.getPermision().getMethod().name()),
                                            String.format("%s%s", apiPrefix, permission.getPermision().getUrl()))
                                    .hasAnyAuthority(role.getRole(), E_Role.ROLE_ADMIN.name());
                        });
                    });
                    r.requestMatchers("/api/v1/**").hasAuthority(E_Role.ROLE_ADMIN.name());
                })

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        });
        http.exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write(authException.getMessage());
//                   throw new AnthorizationException(Const.AUTHORIZATION);
                });

        http.exceptionHandling().accessDeniedHandler(customeAccessDeniedException);
        return http.build();
    }

}
