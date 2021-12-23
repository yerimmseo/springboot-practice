package com.practice.book.springboot.config.auth;

import com.practice.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable.
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점. 선언 되어야만 antMatchers() 사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 권한 관리 대상을 지정하는 옵션. permitAll()을 통해 전체 열람 권한 부여 
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // USER 권한을 가진 사람만 가능하도록 함
                    .anyRequest().authenticated() // 설정된 값들 이외의 URL을 나타냄. authenticated()는 모두 인증된 사용자들에게만 허용하게 함
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점.
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점.
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당함
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조취를 진행할 UserService 인터페이스의 구현체 등록
                                                                   // 리소스서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
    }
}
