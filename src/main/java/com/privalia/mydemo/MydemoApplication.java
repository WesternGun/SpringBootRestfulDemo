package com.privalia.mydemo;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.privalia.mydemo.model.BinInfo;
import com.privalia.mydemo.repo.BinInfoRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MydemoApplication {

	public static void main(String[] args) {
	    log.info("Application launching...");
		SpringApplication.run(MydemoApplication.class, args);
		log.info("Application launched. ");
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//	    return args -> {
//	        System.out.println("Check beans provided by Spring Boot: ->");
//	        String[] beanNames = ctx.getBeanDefinitionNames();
//	        Arrays.sort(beanNames);
//	        for (String name: beanNames) {
//	            System.out.println(name);
//	        }
//	    };
//	}
	
//	@Bean
//	public CommandLineRunner cmdRunnerSave(BinInfoRepository repository) {
//	    return args -> {
//	        log.info("Saving an entity...");
//	        repository.save(new Bin_info("654321", "{'name': 'sss', 'date':'today', 'fields':['bin', 'json_full', 'bank', 'type', 'brand']}", new Date()));
//	        log.info("Querying an entity...");
//	        System.out.println(repository.findById(new Long(1)).toString());
//	        log.info("End");
//	        
//	    };
//	}
}
