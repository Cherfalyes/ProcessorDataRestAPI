package com.sentrysoftware.processordata.processor;

import java.util.concurrent.Callable;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * For every processor, a ProcessorDataHandler instance is created, this ensures the Processors history records fetching and the result calculation parallelism. 
 * It will take care of fetching processor's history records and calculate the min, max or avg according to the request. 
 * It implements Callable interface which is a Thread that returns a result (a ProcessorOutputData in our case)
 * @author Elyes CHERFA
 */
public class ProcessorDataHandler implements Callable<ProcessorOutputData>{

	/**
	 * Processor Name
	 */
	private String processorName;
	/**
	 * The number of processor data records to fetch
	 */
	private int history;
	/**
	 * Contains the processor fetched records
	 */
	private ProcessorDataHistory processorDataHistory;
	/**
	 * Operation type to achieve (min, max or avg) according to the Http request 
	 */
	private ProcessorOperationType type;
	
	/**
	 * Constructor to create a ProcessorDataHandler
	 * @param processorName Name of the processor that the current Callable handle
	 * @param history Number of data records to fetch for the current processor 
	 * @param type Type of operation to do (min, max or avg)
	 */
	public ProcessorDataHandler(String processorName, int history, ProcessorOperationType type) {
		this.processorName = processorName;
		this.history = history;
		this.type = type;
	}
	
	/**
	 * This method is called to start the callable, it will take care of fetching processor history records, and calculate the result to return it as a ProcessorOutputData
	 * @return Processor Output Data [name and (min, max or avg)]
	 */
	@Override
	public ProcessorOutputData call() {
		// trying fetch processor history data
		try {
			this.fetchProcessorHistory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// verifying if the data have been correctly retrieved
		if(processorDataHistory!= null && processorDataHistory.getHistory()!= null && !processorDataHistory.getHistory().isEmpty()) {
			ProcessorOutputData output = new ProcessorOutputData();
			output.setName(processorName);
			// choosing the right method according to type value
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
	
	/**
	 * Retrieve N processor history records from the webservice, where N = history 
	 */
	public void fetchProcessorHistory() throws Exception{ 
		String url = "https://xdemo.sentrysoftware.com/rest/console/NT_CPU/"+ processorName +"/CPUprcrProcessorTimePercent?max=" + history;
		WebClient.Builder builder = WebClient.builder();
		processorDataHistory = builder.build().get().uri(url).retrieve().bodyToMono(ProcessorDataHistory.class).block();
	}
	
	/**
	 * Find the max from the processor history
	 */
	public double getMax() {
		double max = processorDataHistory.getHistory().stream().mapToDouble(d -> d.getValue()).max().orElseThrow();
		return max;
	}
	
	/**
	 * Find the min from the processor history
	 */
	public double getMin() {
		double min = processorDataHistory.getHistory().stream().mapToDouble(d -> d.getValue()).min().orElseThrow();
		return min;
	}
	
	/**
	 * Calculate the average from the processor history
	 */
	public double getAvg() {
		double avg = processorDataHistory.getHistory().stream().mapToDouble(d -> d.getValue()).average().orElseThrow();
		return avg;
	}
	
}
