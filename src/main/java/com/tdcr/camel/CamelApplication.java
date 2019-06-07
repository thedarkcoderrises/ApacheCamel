package com.tdcr.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CamelApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CamelApplication.class, args);

		Runnable target = (Runnable) context.getBean("task");
		Thread t = new Thread(target);
		t.start();
	}

}
