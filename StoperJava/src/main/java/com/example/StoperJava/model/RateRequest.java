package com.example.StoperJava.model;

import java.sql.Date;

import javax.persistence.ManyToOne;

public class RateRequest {

	
	private String comment;
	
	private String mark;
	
	private String evaluatorEmail;
	
	private String recieverEmail;
	
	public RateRequest() {
		super();
	}

	public RateRequest(String comment, String mark,String evaluatorEmail, String recieverEmail) {
		super();
		this.comment = comment;
		this.mark = mark;
		this.recieverEmail=recieverEmail;
		this.evaluatorEmail=evaluatorEmail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getEvaluatorEmail() {
		return evaluatorEmail;
	}

	public void setEvaluatorEmail(String evaluatorEmail) {
		this.evaluatorEmail = evaluatorEmail;
	}

	public String getRecieverEmail() {
		return recieverEmail;
	}

	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}

	
	
	

}
