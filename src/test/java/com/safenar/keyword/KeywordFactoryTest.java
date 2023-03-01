package com.safenar.keyword;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class KeywordFactoryTest {
	@Test
	void createKeyword_forKeywordWithoutCallbackOrArguments() {
		Keyword kw=KeywordFactory.createKeyword("kw");
		assertThat(kw.getName()).isEqualTo("kw");//i don't know what i'm doing with my life
	}
	@Test
	void createKeyword_forKeywordWithCallbackWithoutArguments() {
		int[] counter = {0};//f*cking lambda
		Keyword kw=KeywordFactory.createKeyword("call",args-> counter[0]++);//hopefully there's workaround
		assertThat(kw.getName()).isEqualTo("call");
		kw.execute();//mess with too many errors
		assertThat(counter[0]).isEqualTo(1);
	}
	@Test
	void fromBuilder_forClearKeyword(){
		Keyword kw=KeywordFactory.createFromBuilder().build();
		assertThat(kw.getName()).isEqualTo("");
	}
}