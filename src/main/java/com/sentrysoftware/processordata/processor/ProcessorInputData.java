package com.sentrysoftware.processordata.processor;

import java.util.List;

/**
 * A class representing the Processor Input Data. In this version, we only fetch subnodes array  
 */
public class ProcessorInputData {
	
	/**
	 * A list of processors names 
	 */
	private List<String> subnodes;

	/**
	 * subnodes getter
	 * @return subnodes
	 */
	public List<String> getSubnodes() {
		return subnodes;
	}

	/**
	 * The constructor of ProcessorInputData
	 * @param subnodes
	 */
	public void setSubnodes(List<String> subnodes) {
		this.subnodes = subnodes;
	}

	
}
