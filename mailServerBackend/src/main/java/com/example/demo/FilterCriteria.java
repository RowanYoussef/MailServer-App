package com.example.demo;

import java.util.ArrayList;

public class FilterCriteria {
    
    private String input;
    
   
	public FilterCriteria(String input) {
		super();
		this.input = input;
	}

	 
	  public ArrayList<Email> Filter(Profile p, String whatTOfilter, String filterkey){
	    	switch(whatTOfilter) {
	    	case "Inbox" :
	    		 return meetCriteria(p.getInbox().getEmails(),filterkey);
	    		 
	    	case "Trash" :
	   		 return meetCriteria(p.getTrash().getTrashemails(),filterkey);
	   		 
	    	case "Draft" :
	      		 return meetCriteria(p.getDraft().getDraftemails(),filterkey);
	      		 
	    	case "Sent" :
	    		 return meetCriteria(p.getSent().getSentemails(),filterkey);
	    	}
	    	return null;
	    	
	    }
	
	  public ArrayList<Email> meetCriteria(ArrayList<Email> emails,String filterkey) {
	        switch (filterkey)
	        {
	            case "Subject":
	            {
	                return subjectCriteria(emails);
	            }
	            case "Sender":
	            {
	            	return SenderCriteria(emails);
	            }
	            case "Receiver":
	            {
	            	return SenderAndReceiver(emails);
	            }
	               
	        }
	     
	        return null;
	    }
    
    public ArrayList<Email> subjectCriteria (ArrayList<Email> emails) {
    	ArrayList <Email> filter= new ArrayList<>();
    	for(Email E: emails)
        {
            if(E.getSubject().equalsIgnoreCase(this.input))
            {
                filter.add(E);
            }
        }
    	return filter;
    }
    public ArrayList<Email> SenderCriteria (ArrayList<Email> emails) {
    	ArrayList <Email> filter= new ArrayList<>();
    	for(Email E: emails)
        {
            if(E.getSender().equalsIgnoreCase(this.input))
            {
                filter.add(E);
            }
        }
    	return filter;
    }
    public ArrayList<Email> SenderAndReceiver(ArrayList<Email> emails){
    	ArrayList <Email> filter= new ArrayList<>();
    	for(Email E: emails)
        {
            if(E.getSender().equalsIgnoreCase(this.input)||E.getReceiver().equalsIgnoreCase(this.input))
            {
                filter.add(E);
            }
        }
    	return filter;
    }
    
}
