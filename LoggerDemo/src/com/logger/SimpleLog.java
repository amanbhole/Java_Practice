package com.logger;

import org.apache.log4j.Logger;

public class SimpleLog {

	static Logger logger = Logger.getLogger("SimpleLog.class");
	 
	 
	public static void main(String[] args) {
		
		  logger.debug("Hello world.");

	}
	
}
