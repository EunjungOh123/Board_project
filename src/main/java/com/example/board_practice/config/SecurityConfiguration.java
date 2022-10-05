package com.example.board_practice.config;

import com.example.board_practice.member.service.Impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
WebSecurityConfigurerAdapter은 Deprecated 되었으므로 다른 방법 사용
SecurityFilterChain을 Bean으로 등록해서 사용한다
*/

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfiguration {

    private final AuthFailureHandler authFailureHandler;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        //  BadCredentialException 과 UsernameNotFoundException 별도로 처리하기 위해 사용
        authProvider.setHideUserNotFoundExceptions(false);

        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean // configure(AuthenticationManagerBuilder) 대신 사용
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/member/login","/member/register","/member/email-auth")
                .permitAll(); // 해당 경로들은 접근을 허용
        http.formLogin()
                .loginPage("/member/login")
                // loadUserByUsername에 값이 들어오기 위해서는 로그인 폼에서 password 태그의 name이 userid로 설정
                .usernameParameter("userId")
                .defaultSuccessUrl("/")
                .failureHandler(authFailureHandler)
                .permitAll();

                http.logout() // 로그아웃 설정 진행
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 경로 지정
                .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 경로를 지정
                .invalidateHttpSession(true); // 로그아웃 성공 시 세션을 제거

//        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
