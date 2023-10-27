package com.sentrysoftware.processordata.processor;

import java.util.List;


public class ProcessorDataHistory {

private List<ProcessorDataHistoryRecord> history;
	
	
	public List<ProcessorDataHistoryRecord> getHistory() {
		return history;
	}

	public void setHistory(List<ProcessorDataHistoryRecord> history) {
		this.history = history;
	}
	
	public ProcessorDataHistory(List<ProcessorDataHistoryRecord> history) {
		super();
		this.history = history;
	}

	public ProcessorDataHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
}
