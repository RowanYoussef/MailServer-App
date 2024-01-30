package com.example.demo;

import java.util.ArrayList;

public class Email {
    private String sender, subject, body, date, priority,id, receiver;
    private ArrayList<Attachment> attachments;
    

    public Email() {
    	this.attachments=new ArrayList<>();
    }
    public Email(String sender, String receiver,String subject, String body,String id,
                 String date, String priority/* ArrayList<Attachment> attachments*/){
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
        this.date = date;
        this.priority = priority;
        this.id=id;
        this.attachments=new ArrayList<>();
        
    }
    
	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
