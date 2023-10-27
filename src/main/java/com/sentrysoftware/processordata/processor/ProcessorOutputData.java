package com.sentrysoftware.processordata.processor;

/**
 * This class is used to return the result (name, value)
 */
public class ProcessorOutputData {

	/**
	 * name processor name
	 */
	private String name;
	
	/**
	 * value output value (min, max or avg)
	 */
	private double value;
	
	
	/**
	 * name getter
	 * @return processor name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * name setter
	 * @param processor name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * value getter
	 * @return processor output value
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * value setter
	 * @param processor output value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * ProcessorOutputData constructor using fields
	 * @param processor output value
	 * @param processor name
	 */
	public ProcessorOutputData(String name, double value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	/**
	 * ProcessorOutputData default constructor
	 */
	public ProcessorOutputData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
