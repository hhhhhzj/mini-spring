package com.test.service;

public class AServiceImpl implements AService {

	private String name;
	private int level;
	private String property1;
	private String property2;

	public AServiceImpl(String name, int level) {
		this.name = name;
		this.level = level;
		System.out.println(this.name + "," + this.level);
	}

	@Override
	public void sayHello() {
		System.out.println(property1 + " " + property2);
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}
}
