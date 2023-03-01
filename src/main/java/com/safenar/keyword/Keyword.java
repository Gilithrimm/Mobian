package com.safenar.keyword;

import com.safenar.*;

public class Keyword implements Command {
	private final String name;
	private final Command cmd;
	private Argument[] args;
	Keyword(String name) {
		this(name,args -> {});
	}
	 Keyword(String name, Command cmd,Argument... args) {
		this.name = name;
		this.cmd = cmd;
		this.args=args;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void execute(Argument... args) {
		cmd.execute(args);
	}
}
