package com.sentrysoftware.processordata.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProcessorController {
	
	@Autowired
	private ProcessorService service;
	
	@GetMapping("/processors/CPUprcrProcessorTimePercent/max")
	public ResponseEntity<List<ProcessorOutputData>> getMaxProcessorsTime(@RequestParam int history){
		return service.getProcessorsTime(history, ProcessorOperationType.MAX);
	}
	
	@GetMapping("/processors/CPUprcrProcessorTimePercent/min")
	public ResponseEntity<List<ProcessorOutputData>> getMinProcessorsTime(@RequestParam int history){
		return service.getProcessorsTime(history, ProcessorOperationType.MIN);
	}
	
	@GetMapping("/processors/CPUprcrProcessorTimePercent/avg")
	public ResponseEntity<List<ProcessorOutputData>> getAverageProcessorsTime(@RequestParam int history){
		return service.getProcessorsTime(history, ProcessorOperationType.AVG);
	}

}
