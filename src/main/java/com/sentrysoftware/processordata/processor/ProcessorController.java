package com.sentrysoftware.processordata.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This represents the REST API Controller, which is responsible of handling http requests and sending back the response with a http code.
 * @author Cherfa Elyes
 */
@RestController
public class ProcessorController {
	/**
	 * Using the Processor Service to handle the response
	 */
	@Autowired
	private ProcessorService service;
	/**
	 * Handling get request to get Maximum CPU time for all the processors   
	 * @param history size of the history sample to study to calculate the maximum
	 * @return response entity with a processor output data (name, max)
	 */
	@GetMapping("/processors/CPUprcrProcessorTimePercent/max")
	public ResponseEntity<List<ProcessorOutputData>> getMaxProcessorsTime(@RequestParam int history){
		return service.getProcessorsTime(history, ProcessorOperationType.MAX);
	}
	/**
	 * Handling get request to get Minimum CPU time for all the processors   
	 * @param history size of the history sample to study to calculate the minimum
	 * @return response entity with a processor output data (name, min)
	 */
	@GetMapping("/processors/CPUprcrProcessorTimePercent/min")
	public ResponseEntity<List<ProcessorOutputData>> getMinProcessorsTime(@RequestParam int history){
		return service.getProcessorsTime(history, ProcessorOperationType.MIN);
	}
	/**
	 * Handling get request to get the Average CPU time for all the processors
	 * @param history size of the history sample to study to calculate the average
	 * @return response entity with a processor output data (name, avg)
	 */
	@GetMapping("/processors/CPUprcrProcessorTimePercent/avg")
	public ResponseEntity<List<ProcessorOutputData>> getAverageProcessorsTime(@RequestParam int history){
		return service.getProcessorsTime(history, ProcessorOperationType.AVG);
	}

}
