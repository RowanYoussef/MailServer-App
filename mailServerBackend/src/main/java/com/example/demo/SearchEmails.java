package com.example.demo;

import java.util.ArrayList;

public class SearchEmails {
	
    public ArrayList<Email> search(Profile p,String key, String whatTOsearch, String content){
    	switch(whatTOsearch) {
    	case "Inbox" :
    		 return Search(key,p.getInbox().getEmails(),content);
    		 
    	case "Trash" :
   		 return Search(key,p.getTrash().getTrashemails(),content);
   		 
    	case "Draft" :
      		 return Search(key,p.getDraft().getDraftemails(),content);
      		 
    	case "Sent" :
    		 return Search(key,p.getSent().getSentemails(),content);
    	}
    	return null;
    	
    }
	   public ArrayList<Email> Search (String key,ArrayList<Email> emails, String content){
		   switch(key){
		   case "sender":
			   return SearchSender(content,emails);
			   
		   case "receiver" :
			   return SearchReciever(content,emails);
			   
		   case "subject" :
			   return SearchSubject(content,emails);
			   
		   case "body" :
			   return Searchbody(content,emails);
			   
		   case "priority" :
			   return SearchPriority(content,emails);
			 
		   }
		   return null;
	   }
	    
	
	
	   public ArrayList<Email> SearchSender(String sender,ArrayList<Email> emails){
	    	ArrayList<Email> searched = new ArrayList<>();
	    	for(Email e: emails) {
	    		if(e.getSender().toLowerCase().contains(sender.toLowerCase())) {
	    			searched.add(e);
	    		}		
	    	}
	    	return searched;
	    }
	   public ArrayList<Email> SearchReciever(String reciever,ArrayList<Email> emails){
	    	ArrayList<Email> searched = new ArrayList<>();
	    	for(Email e: emails) {
	    		if(e.getReceiver().toLowerCase().contains(reciever.toLowerCase())) {
	    			searched.add(e);
	    		}		
	    	}
	    	return searched;
	    }
	   public ArrayList<Email> Searchbody(String body,ArrayList<Email> emails){
	    	ArrayList<Email> searched = new ArrayList<>();
	    	for(Email e: emails) {
	    		if(e.getBody().toLowerCase().contains(body.toLowerCase())) {
	    			searched.add(e);
	    		}		
	    	}
	    	return searched;
	    }
	   public ArrayList<Email> SearchSubject(String subject,ArrayList<Email> emails){
	    	ArrayList<Email> searched = new ArrayList<>();
	    	for(Email e: emails) {
	    		if(e.getSubject().toLowerCase().contains(subject.toLowerCase())) {
	    			searched.add(e);
	    		}		
	    	}
	    	return searched;
	    }
	   public ArrayList<Email> SearchPriority(String priority,ArrayList<Email> emails){
	    	ArrayList<Email> searched = new ArrayList<>();
	    	for(Email e: emails) {
	    		if(e.getPriority().toLowerCase().contains(priority.toLowerCase())) {
	    			searched.add(e);
	    		}		
	    	}
	    	return searched;
	    }
	   
}
