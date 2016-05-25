package com.mkyong.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mkyong.transaction.TransactionBo;

@Component
@Produces({ "application/json"})
@Path("/payment")
public class PaymentService {

	@Autowired
	TransactionBo transactionBo;

	@GET
	@Path("/mkyong/get")
	public Response savePayment() {

		String result = transactionBo.save();

		return Response.status(200).entity(result).build();

	}
	
	@DELETE
	@Path("/mkyong/delete")
	public Response savePayment1() {

		String result = transactionBo.save();

		return Response.status(200).entity(result).build();

	}
	
	@PUT
	@Path("/mkyong/put")
	public Response savePayment2(@HeaderParam("inp") String inp) {
		
		if(inp!=null)
			System.out.println("Coming");
		String result = transactionBo.save();

		return Response.status(200).entity(result).build();

	}
	
	@POST
	@Produces({ "application/xml"})
	@Path("/mkyong/post")
	public Response savePayment3() {

		String result = transactionBo.save();
//		return result;
		return Response.status(200).entity(result).build();

	}

}