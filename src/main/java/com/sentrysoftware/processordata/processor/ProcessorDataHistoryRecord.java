package com.sentrysoftware.processordata.processor;

/**
 * This represents one Processor Data History Record with a value and a timestamp
 */
public class ProcessorDataHistoryRecord {

	/**
	 * record value
	 */
	private double value;
	
	/**
	 * record timestamp
	 */
	private String timestamp;

	/**
	 * value getter
	 * @return value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * value setter
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * timestamp getter
	 * @return timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * timestamp setter
	 * @param record timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * ProcessorDataHistoryRecord constructor using fields
	 * @param value
	 * @param timestamp
	 */
	public ProcessorDataHistoryRecord(double value, String timestamp) {
		super();
		this.value = value;
		this.timestamp = timestamp;
	}

	/**
	 * ProcessorDataHistoryRecord default constructor
	 */
	public ProcessorDataHistoryRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

}
