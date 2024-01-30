package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;

public class Draft {
	ArrayList<Email> Draftemails = new ArrayList<>();

	public ArrayList<Email> getDraftemails() {
//		 ArrayList <Email> reversed = (ArrayList<Email>) Draftemails.clone();
//		   Collections.reverse(reversed);
		return this.Draftemails;
	}
    
	public void setDraftemails(ArrayList<Email> draftemails) {
		Draftemails = draftemails;
	}
	
	public void addToDraft(Email email) {
		this.Draftemails.add(email);
	}
	
	public void removefromDraft(String id) {
		for(Email e: Draftemails) {
			   if(e.getId().equals(id))
				   Draftemails.remove(e);
			
		   }
	   }
	
	}
	
	

