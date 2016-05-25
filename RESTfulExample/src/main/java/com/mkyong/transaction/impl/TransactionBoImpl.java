package com.mkyong.transaction.impl;

import org.springframework.stereotype.Repository;

import com.mkyong.transaction.TransactionBo;

@Repository("TransactionBo")
public class TransactionBoImpl implements TransactionBo {

/*	public TransactionBoImpl() {
		
	}*/
	public String save() {

		return "Jersey + Spring example";

	}

}