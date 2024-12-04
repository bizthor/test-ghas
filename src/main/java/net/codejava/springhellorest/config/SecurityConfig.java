package net.codejava.springhellorest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Value("${application.security.cors.allowed.domains}")
    private String allowedDomains;

    @Value("${application.security.ignoredPaths}")
    private String ignoredPaths;
    

    
    @Override
    public void configure(WebSecurity web) {
        //URL donde no aplicara la seguridad, por ejemplo swagger
        web.ignoring().antMatchers(ignoredPaths.trim().split(","));
    }
    
    
    @Override   
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
            .antMatchers("/api/**").permitAll();
 
        //AuthenticationEntryPoint entryPoint = new JwtValidationEntryPoint();
        //http.exceptionHandling().authenticationEntryPoint(entryPoint);
        
        //http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();

//        http.addFilterAfter(new JwtValidationFilter(keyStoreType, keyStorePath, keyAlias, keyStorePassword),
//                SecurityContextPersistenceFilter.class);        
        
 
    }
    

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowCredentials(true)
                .allowedOrigins(allowedDomains.split(","))
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }

}