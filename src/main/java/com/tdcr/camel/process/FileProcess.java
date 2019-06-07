package com.tdcr.camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("fileProcess")
public class FileProcess implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		if(exchange.getIn().hasHeaders()){
			System.out.println("Has Header");
			Map<String,Object> temp =exchange.getIn().getHeaders();
			for (String str : exchange.getIn().getHeaders().keySet()) {
				
				System.out.println("Key :"+str);
				System.out.println("Val :"+temp.get(str));
			}
			
		}else{
			System.out.println("No headers");
		}

		
		
		if (exchange.getIn().getBody(String.class) != null) {
			Message in = exchange.getIn();
	        in.setBody(in.getBody(String.class) + " ta da!");
		}
	}

}
