package com.blog.config;
import com.blog.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userDetailsService;
   @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    //    @Override
//    @Bean
//    protected UserDetailsService userDetailsService(){
//        UserDetails user = User.builder().username("Abhi").password(passwordEncoder.encode("password")).roles("ADMIN").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user,admin);
    //   }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getEncodePassword());
    }
    @Bean
    public PasswordEncoder getEncodePassword(){
       return new BCryptPasswordEncoder();
    }
}
