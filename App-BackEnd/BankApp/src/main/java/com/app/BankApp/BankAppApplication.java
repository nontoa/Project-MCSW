package com.app.BankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; //libreria importada problema cors
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; 

@SpringBootApplication
@EnableWebMvc
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

//código añadido recientemente problemas de cors en browser
        
@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                       .allowedOrigins("*")
                       .allowedMethods("GET", "POST", "PUT", "DELETE")
                       .allowedHeaders("*");
            }
        };
   }        

}
