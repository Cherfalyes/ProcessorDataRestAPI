package com.sentrysoftware.processordata.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * This class is used to handle the rest API Request, this implements a ProcessorService interface
 * @author Elyes CHERFA
 */
@Service
public class ProcessorService implements ProcessorServiceInterface{

	/**
	 * Contains the names of all the processors
	 */
	private List<String> processors;
	//private ResponseEntity<List<ProcessorOutputData>> response;
	
	/**
	 * Used to 
	 * @param History Number of data records to fetch from the processor data history
	 * @param type Processor Data Type to return back (Min, Max or Average) 
	 * @return A Response Entity with the output data of all the processors (if everything works perfectly) and a Http code to describe the Http status. Otherwise, it returns a HttpStatus only.
	 */
	@Override
	public ResponseEntity<List<ProcessorOutputData>> getProcessorsTime(int history, ProcessorOperationType type) {
		// Create the response entity and initialize it with an Unavailable Service Http status
		ResponseEntity<List<ProcessorOutputData>> response = new ResponseEntity<List<ProcessorOutputData>>(HttpStatus.SERVICE_UNAVAILABLE);
		// Fetch Processors names when the service is created
		try {
			this.fetchProcessorsData();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		if(processors!= null && !processors.isEmpty()) {
			// create an executor that will invoke the different Callables (Threads with a response)
			ExecutorService executor = Executors.newFixedThreadPool(processors.size());
			// create a ProcessorInfoHandler for each processor and add its reference to the handler list 
			List<ProcessorDataHandler> handlers = new ArrayList<ProcessorDataHandler>();
			processors.parallelStream().forEach(processor -> {
				ProcessorDataHandler handler = new ProcessorDataHandler(processor, history, type);
				handlers.add(handler);
			});
			
			// Execute all the ProcessorInfoHandlers at the same time using an executor
			// The executor will resume when all the handlers finish their work
			List<Future<ProcessorOutputData>> results = new ArrayList<Future<ProcessorOutputData>>();
			try {
				results = executor.invokeAll(handlers);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			// turn off the executor
			executor.shutdown();
			
			// Retrieve each result from the Future object
			List<ProcessorOutputData> outputs = new ArrayList<ProcessorOutputData>();
			for(Future<ProcessorOutputData> futureOutput : results) {
				try {
					if(futureOutput.get() != null) {
						outputs.add(futureOutput.get());
					}
				
				} catch (InterruptedException | ExecutionException e) { 
						e.printStackTrace();
				}
			}
							
			if(!outputs.isEmpty()) {
				// Sort the results in descending numerical order based on value
				Collections.sort(outputs, Comparator.comparing(ProcessorOutputData::getValue).reversed());
				// Everything worked fine, we'll return the output to the controller and a 200 status code
				response = new ResponseEntity<List<ProcessorOutputData>>(outputs, HttpStatus.OK);
			}
		}
		return response;
	}

	/**
	 * Fetch the processors data from the webservice 
	 */
	@Override
	public void fetchProcessorsData() throws Exception{
		// Web service URL
		String url = "https://xdemo.sentrysoftware.com/rest/namespace/NT_CPU";
		// Create a webclient builder that serves to use Http requests
		WebClient.Builder builder = WebClient.builder();
		// A get request to retrieve all the Processor Input Data from the web service (only names in this case excluding CPU__TOTAL
		ProcessorInputData inputData = builder.build().get().uri(url).retrieve().bodyToMono(ProcessorInputData.class).block(); 
		//processors = builder.build().get().uri(url).retrieve().bodyToMono(ProcessorInputData.class).block().getSubnodes();
		processors = inputData.getSubnodes();
		if(processors != null) {
			processors.remove("CPU__Total");
		}
	}
}
