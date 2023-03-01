package com.safenar;

import com.safenar.keyword.Keyword;
import com.safenar.keyword.KeywordFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParserTest {
	private int counter=0;
	private void add(int num){
		counter+=num;
	}
	@BeforeEach
	void setUp(){
		counter=0;
	}
	@Test
	void from_forKeywordsWithoutArguments() {
		Keyword kw1= KeywordFactory.createKeyword("incr",a->add(1));
		Parser parser=new Parser();
//		parser.from(kw1);
		assertThat(parser);
	}
}