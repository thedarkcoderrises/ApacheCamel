package com.tdcr.camel.routeBuilder;

import com.tdcr.camel.exchangeBean.ExchangeBean;
import com.tdcr.camel.process.FileProcess;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CamelRouteBuilder extends RouteBuilder {
	
	@PostConstruct
	void init(){
		System.out.println("CamelRouteBuilder");
	}
	
	@Autowired
	FileProcess fp;

	@Override
	public void configure() throws Exception {
		from("file:/Users/javabrain/Documents/MyWorkspace/camel/upStream").autoStartup(true).routeId("upStream")
		.to("jms:queue:DEMO-JMS-QUEUE");
		//.to("file:/Users/javabrain/Documents/MyWorkspace/camel/downStream");


		from("jms:queue:DEMO-JMS-QUEUE").routeId("downStream")
//		.bean(ExchangeBean.class,"doSomething") // way 1
		.process(fp).log("calling process")// way 2
		.choice()
        .when(header("CamelFileNameOnly").endsWith(".text"))
            .to("file:/Users/javabrain/Documents/MyWorkspace/camel/downStream/txt")
        .when(header("CamelFileNameOnly").endsWith(".xml"))
            .to("file:/Users/javabrain/Documents/MyWorkspace/camel/downStream/xml")
        .otherwise()
            .to("file:/Users/javabrain/Documents/MyWorkspace/camel/downStream/invalid");
		
	}

}
