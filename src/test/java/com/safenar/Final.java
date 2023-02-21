package com.safenar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FinalTest {
	private static String log;
	
	@Test
	void testApi1(){
		Keyword hello=API.createKeyword("hello")
				.withArgument(String.class)
				.withCalllback(args->println("Hello, "+args+"!"))
				.build();
		API.parser.from(KeywordSet.of(hello)).parse("hello world");
		assertThat(log).endsWith("Hello, world!");
	}
	private static void println(Object msg) {
		log+=msg.toString();
		System.out.println(msg);
	}
}
