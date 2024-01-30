package com.example.demo;

import java.util.ArrayList;

public class Contact {
	  private String name;
	  ArrayList<String> usernames;
	  
	    public Contact(String name) {
		super();
		this.name = name;
		usernames= new ArrayList<>();
	}

		public ArrayList<String> getUsernames() {
			return usernames;
		}

		public void setUsernames(ArrayList<String> usernames) {
			this.usernames = usernames;
		}

		public Contact(){}

	    public String getName() {
	        return this.name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	    public void addusername(String username,ArrayList<Profile> profiles) {
	    	if(isExist(username,profiles))
	    	this.usernames.add(username);
	    }
		  public boolean isExist(String contactname,ArrayList<Profile> profiles) {
			  for(Profile p: profiles)
				  if(p.getUsername().equals(contactname))
	                   return true;
	          return false;
		  }

}
