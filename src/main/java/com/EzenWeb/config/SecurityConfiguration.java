package com.EzenWeb.config;

import com.EzenWeb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .defaultSuccessUrl("/member/")
                .failureUrl("/member/login")
                .usernameParameter("memail")
                .passwordParameter("mpassword")

            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/")
                .invalidateHttpSession(true)
            .and()
                .csrf()
                .ignoringAntMatchers("/member/loginprocess")
                .ignoringAntMatchers("/member/setmember")
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
