package com.safenar.keyword;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class KeywordFactory {//rework?
	public static Keyword createKeyword(String name) {
		return new Keyword(name);
	}
	public static Keyword createKeyword(String name, Consumer<Object[]> cmd) {
		return new Keyword(name,cmd);
	}
	public static Builder createFromBuilder() {
		return Builder.BUILDER;
	}
	public static class Builder{
		private static final Builder BUILDER=new Builder();
		private String name="";
		private final Consumer<Object[]> cmd=args->{};
		private final List<Object> args=new ArrayList<>();
		public Keyword build() {
			Keyword result= new Keyword(name,cmd);
//			result.addArguments
			return result;
		}
		public Builder withName(String name) {
			this.name=name;
			return this;
		}
		public Builder withArgument(Object arg) {
			args.add(arg);
			return this;
		}
	}
}
