package com.sentrysoftware.processordata.processor;

import java.util.List;

/**
 * This class is used to fetch A Processor Data. Even if the usage of this class seam to be pointless in our case since we need only one Processor Data (history records), the creation of such a class makes the application evolution easy as some other processor data may be required in the future.
 */
public class ProcessorDataHistory {

	/**
	 * processor history, which represents a List of HistoryInfos, where each History Info is a record of the processor data.
	 */
	private List<ProcessorDataHistoryRecord> history;
	
	/**
	 * @return history
	 */
	public List<ProcessorDataHistoryRecord> getHistory() {
		return history;
	}

	/**
	 * @param history
	 */
	public void setHistory(List<ProcessorDataHistoryRecord> history) {
		this.history = history;
	}
	
	/**
	 * ProcessorDataHistory constructor
	 * @param history
	 */
	public ProcessorDataHistory(List<ProcessorDataHistoryRecord> history) {
		super();
		this.history = history;
	}

	/**
	 * default ProcessorDataHistory constructor
	 */
	public ProcessorDataHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
}
