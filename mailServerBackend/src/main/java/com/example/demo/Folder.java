package com.example.demo;

import java.util.ArrayList;

public class Folder {
	
     private String foldername;
     ArrayList<Email> emails;
     
	public Folder(String foldername) {
		super();
		this.foldername = foldername;
		emails= new ArrayList<>();
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public ArrayList<Email> getEmails() {
		for(Email e:emails) {
			if(e==null)
				emails.remove(e);
		}
		return emails;
	}
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}
	public void addEmail(Email email) {
		this.emails.add(email);
	}
    public Email DeleteFolderEmail(String id) {
    	for(Email e:emails) {
    		if(e.getId().equals(id)) {
    			emails.remove(e);
    			return e;
    		}
    	}
    	return null;
    }
     
     
}
