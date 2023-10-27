package com.sentrysoftware.processordata.processor;

import java.util.concurrent.Callable;

import org.springframework.web.reactive.function.client.WebClient;

public class ProcessorDataHandler implements Callable<ProcessorOutputData>{

	private String processorName;
	private int history;
	private ProcessorDataHistory processorDataHistory;
	private ProcessorOperationType type;
	
	public ProcessorDataHandler(String processorName, int history, ProcessorOperationType type) {
		this.processorName = processorName;
		this.history = history;
		this.type = type;
	}
	
	@Override
	public ProcessorOutputData call() {
		//
		try {
			this.fetchProcessorHistory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(processorDataHistory!= null && processorDataHistory.getHistory()!= null && !processorDataHistory.getHistory().isEmpty()) {
			ProcessorOutputData output = new ProcessorOutputData();
			output.setName(processorName);
			// 
			switch(type) {
			case MAX :
				output.setValue(this.getMax());
				break;
			case MIN:
				output.setValue(this.getMin());
				break;
			case AVG:
				output.setValue(this.getAvg());
				break; 
			}
			return output;
		}
		return null;
					
	}
	
	public void fetchProcessorHistory() throws Exception{ 
		String url = "https://xdemo.sentrysoftware.com/rest/console/NT_CPU/"+ processorName +"/CPUprcrProcessorTimePercent?max=" + history;
		WebClient.Builder builder = WebClient.builder();
		processorDataHistory = builder.build().get().uri(url).retrieve().bodyToMono(ProcessorDataHistory.class).block();
	}
	
	public double getMax() {
		double max = processorDataHistory.getHistory().stream().mapToDouble(d -> d.getValue()).max().orElseThrow();
		return max;
	}
	
	public double getMin() {
		double min = processorDataHistory.getHistory().stream().mapToDouble(d -> d.getValue()).min().orElseThrow();
		return min;
	}
	
	public double getAvg() {
		double avg = processorDataHistory.getHistory().stream().mapToDouble(d -> d.getValue()).average().orElseThrow();
		return avg;
	}
	
}
