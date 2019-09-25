package com.eLibraryBatch.batchMailbusiness;
import com.eLibraryBatch.batchMailbusiness.config.MailConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients("com.eLibraryBatch")
@ComponentScan(basePackages = {"com.eLibraryBatch"})
public class BatchMailBusinessApplication  {

	public static void main(String[] args) {
		//test
//		System.setProperty("systemValue", "Some system parameter value");
//		System.setProperty("priority", "System property");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MailConfig.class);

		//ancien
		SpringApplication.run(BatchMailBusinessApplication.class, args);}
	}
