package com.example.demo;

public class Attachment {
    private byte[] attachment;
    private String contentType;
    private String filename;
    
    
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	
}
