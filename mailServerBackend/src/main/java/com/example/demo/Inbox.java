package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Inbox {
   ArrayList<Email> emails = new ArrayList<>();  
   
   public ArrayList<Email> getForWardOrBackWard(int state){	
//	   ArrayList <Email> reversed = (ArrayList<Email>) emails.clone();
//	   Collections.reverse(reversed);
	   if(state*3+3>emails.size())
		   return new ArrayList<Email> (emails.subList((state*3), emails.size()));
	   return new ArrayList<Email> (emails.subList((state*3), (state*3+3)));
   }
 

public ArrayList<Email> getEmails() {
	 for(Email e: emails) {
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

public Email removeEmail(String id) {
	for(Email e: emails) {
   if(e.getId().equals(id)) {
	   emails.remove(e);
       return e;
   }
	}
	return null;
 
}


   
}
