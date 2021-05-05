package com.chap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
	//	SpringApplication.run(Main.class, args);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Appcontext.class);
		Greeter g = ctx.getBean("greeter",Greeter.class);
		String msg = g.greet("스프링");
		System.out.println(msg);
		ctx.close();
	}

}
