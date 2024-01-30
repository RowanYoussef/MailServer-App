package com.example.demo;

import java.util.ArrayList;

public class ContactsProfile {
	 private ArrayList<Contact> contacts;
	 
	 
	public ContactsProfile() {
		super();
		this.contacts = new ArrayList<>();
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	

	 public void addContact (String contactname){
	          this.contacts.add(new Contact(contactname));
	    }

	 public void deleteContact(String conName){
	        for(Contact c : contacts) {
	        	if(c.getName().equals(conName))
	        		contacts.remove(c);
	        }
	    }
	 public Contact GetcontactByName(String contactname) {
		 for(Contact c : contacts) 
	        	if(c.getName().equals(contactname))
	        		return c;
		 return null;
	 }
	 
}
