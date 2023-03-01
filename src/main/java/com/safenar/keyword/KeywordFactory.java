package com.safenar.keyword;

import com.safenar.Argument;
import com.safenar.Command;

import java.util.ArrayList;
import java.util.List;

public class KeywordFactory {
	public static Keyword createKeyword(String name) {
		return new Keyword(name);
	}
	public static Keyword createKeyword(String name, Command cmd) {
		return new Keyword(name,cmd);
	}
	public static Builder createFromBuilder() {
		return Builder.BUILDER;
	}
	public static class Builder{
		private static final Builder BUILDER=new Builder();
		private String name="";
		private Command cmd=args->{};
		private List<Argument> args=new ArrayList<>();
		public Keyword build() {
			Keyword result= new Keyword(name,cmd);
//			result.addArguments
			return result;
		}
		public Builder withName(String name) {
			this.name=name;
			return this;
		}
		public Builder withArgument(Argument arg) {
			args.add(arg);
			return this;
		}
	}
}
