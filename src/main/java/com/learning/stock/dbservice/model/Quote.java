package com.learning.stock.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quote")
public class Quote {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="quote")
	private String quote;

	public Quote() {
		super();
	}

	
	public Quote(String userName, String quote) {
		super();
		this.userName = userName;
		this.quote = quote;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	
}
