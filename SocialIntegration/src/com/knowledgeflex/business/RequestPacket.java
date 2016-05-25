/*
 * 
 */
package com.knowledgeflex.business;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestPacket.
 */
public class RequestPacket {
	
	/** The url. */
	public String url;
	
	/** The method. */
	public String method;
	
	/** The body. */
	public String body;
	// header key - value pair
	/** The paramters. */
	public HashMap<String, String> paramters;
	
	/**
	 * Instantiates a new request packet.
	 *
	 * @param url the url
	 * @param method the method
	 * @param paramters the paramters
	 * @param body the body
	 */
	public RequestPacket(String url, String method, HashMap<String, String> paramters, String body) {
		this.url = url;
		this.method = method;
		this.body = body;
		this.paramters = paramters;
//		uid = System.currentTimeMillis();
	}

}
