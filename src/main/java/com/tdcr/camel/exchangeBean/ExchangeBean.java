package com.tdcr.camel.exchangeBean;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

public class ExchangeBean {
	@Handler
	public void doSomething(Exchange exchange){
		exchange.getIn().setBody(exchange.getIn().getBody(String.class)+" \nBye World");
	}


}
