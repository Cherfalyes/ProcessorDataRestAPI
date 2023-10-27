package com.sentrysoftware.processordata.processor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProcessorService implements ProcessorServiceInterface{

	@Override
	public ResponseEntity<List<ProcessorOutputData>> getProcessorsTime(int history, ProcessorOperationType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fetchProcessorsData() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
