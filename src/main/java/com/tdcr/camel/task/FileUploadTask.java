package com.tdcr.camel.task;

import com.tdcr.camel.routeBuilder.CamelRouteBuilder;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;

@Component("task")
public class FileUploadTask implements Runnable{

	@Autowired
	CamelRouteBuilder srb;
	

	@Autowired
	ConnectionFactory connectionFactory;


	@Autowired
	CamelContext camelContext;

	
	@PostConstruct
	void init(){
		System.out.println("FileUploadTask");
	}
	
	@Override
	public void run() {
		try {
			camelContext.start();
            Thread.sleep(5 * 60 * 1000);
            camelContext.stop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

		
	}

}
