package com.sentrysoftware.processordata.processor;

public class ProcessorOutputData {

	private String name;
	private double value;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public ProcessorOutputData(String name, double value) {
		super();
		this.name = name;
		this.value = value;
	}
	public ProcessorOutputData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
