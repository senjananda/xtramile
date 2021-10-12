package com.senja.xtramile.sample;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    @Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
