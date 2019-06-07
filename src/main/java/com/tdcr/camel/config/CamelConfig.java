package com.tdcr.camel.config;

import com.tdcr.camel.routeBuilder.CamelRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.ConnectionFactory;


@Configuration
@EnableAsync
@EnableScheduling
@EnableJms
@ComponentScan(basePackages= {"com.tdcr.*"} )
public class CamelConfig {
	@Autowired
	CamelRouteBuilder camelRouteBuilder;

	@Bean(name="camelContext")
	@ConditionalOnBean(RoutesBuilder.class)
	public CamelContext  defaultCamelContext() {
		CamelContext cc = new DefaultCamelContext();
		try {
			cc.addRoutes(camelRouteBuilder);
			cc.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(activeMQConnectionFactory()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cc;
	}
	
	
	@Bean(name="connectionFactory")
	public ConnectionFactory activeMQConnectionFactory(){
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	} 
	
}
