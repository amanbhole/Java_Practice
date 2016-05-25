package com.logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class SimpleLogWithConfigurator {

	static Logger logger = Logger.getLogger("SimpleLogWithConfigurator.class");

	public static void main(String[] args) {
		
//		SimpleLog.main(args);
		
		BasicConfigurator.configure();
		logger.debug("Hello world.");

	}

}