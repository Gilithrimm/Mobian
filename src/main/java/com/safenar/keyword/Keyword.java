package com.safenar.keyword;

import java.util.function.Consumer;

public class Keyword implements Consumer<Object[]> {
	private final String name;
	private final Consumer<Object[]> cmd;
	private final Object[] args;
	Keyword(String name) {
		this(name,args -> {});
	}
	Keyword(String name, Consumer<Object[]> cmd,Object... args) {
		this.name = name;
		this.cmd = cmd;
		this.args=args;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void accept(Object... args) {
		cmd.accept(args);
	}

}
