package com.corellana.pokedex;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@ComponentScan(basePackages = { "com.corellana.pokedex", "com.corellana.pokedex.api" , "com.corellana.pokedex.config", "com.corellana.pokedex.service"})
public class PokedexApplication {


    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
    
    /* TODO:
     * 
     * - Test
     */
}
