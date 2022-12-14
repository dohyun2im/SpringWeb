package com.EzenWeb.config;

import com.EzenWeb.service.MemberService;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemberService memberService;

    @Override
    protected void configure(HttpSecurity http)throws  Exception{
        http.formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/loginprocess")
                .defaultSuccessUrl("/member/home")
                .failureUrl("/member/login")
                .usernameParameter("memail")
                .passwordParameter("mpassword")

            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/kimdohyun"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and()
                .csrf()
                .ignoringAntMatchers("/member/loginprocess")
                .ignoringAntMatchers("/member/setmember")
                .ignoringAntMatchers("/board/setcategory")
                .ignoringAntMatchers("/board/getcategory")
                .ignoringAntMatchers("/board/setboard")
                .ignoringAntMatchers("/board/getboards")
                .ignoringAntMatchers("/board/deleteboard")
                .ignoringAntMatchers("/board/updateboard")
            .and()
                .oauth2Login()
                .defaultSuccessUrl("/member/")
                .userInfoEndpoint()
                .userService(memberService);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
