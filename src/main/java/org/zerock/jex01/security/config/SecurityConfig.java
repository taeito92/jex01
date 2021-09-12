package org.zerock.jex01.security.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //Security 접목을 위해 사용
@Log4j2
//요즘에는 어노테이션으로 하지만 옛날 방식(상속)으로 진행
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  /*  {
        log.info("Security Config....................");
        log.info("Security Config....................");
        log.info("Security Config....................");
        log.info("Security Config....................");
        log.info("Security Config....................");
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/sample/doAll").permitAll() //doAll에 권한 부여
                .antMatchers("/sample/doMember").access("hasRole('ROLE_MEMBER')")
                .antMatchers("/sample/doAdmin").access("hasRole('ROLE_ADMIN')");
        http.formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("member1")
                .password("$2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu") //member1을 암호화한 코드
                .roles("MEMBER"); //여기서 권한을 부여할 때는 ROLE_을 따로 안붙여도됨.
        auth.inMemoryAuthentication().withUser("admin1")
                .password("$2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu")
                .roles("MEMBER", "ADMIN"); //여기서 권한을 부여할 때는 ROLE_을 따로 안붙여도됨.
    }
}
