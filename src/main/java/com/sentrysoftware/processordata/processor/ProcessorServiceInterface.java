package com.sentrysoftware.processordata.processor;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * ProcessorServiceInterface is used to normalize the service. 
 * Thus, if another implementation is created, it will respect the same methods signatures as the current one, which makes the refactoring and the evolution much easier.
 */
public interface ProcessorServiceInterface {

	public ResponseEntity<List<ProcessorOutputData>> getProcessorsTime(int history, ProcessorOperationType type);
	public void fetchProcessorsData() throws Exception;
}
