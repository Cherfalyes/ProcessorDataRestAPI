package com.sentrysoftware.processordata.processor;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ProcessorServiceInterface {

	public ResponseEntity<List<ProcessorOutputData>> getProcessorsTime(int history, ProcessorOperationType type);
	public void fetchProcessorsData() throws Exception;
}
