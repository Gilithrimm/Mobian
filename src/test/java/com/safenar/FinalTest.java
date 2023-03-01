package com.safenar;

import com.safenar.keyword.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
public class FinalTest {
	private static String log;
	
	@Disabled
	@Test
	void testApi1(){
		Keyword hello=KeywordFactory.createFromBuilder()
				.withName("hello")
				.withArgument(new Argument("txt"))
//				.withCallback(txt->println("Hello, "+txt+"!"))
				.build();
		Parser parser=new Parser();
//		parser.from(hello).parse("hello world");
		assertThat(log).endsWith("Hello, world!");
	}
	private static void println(Object msg) {
		log+=msg.toString();
		System.out.println(msg);
	}
}
