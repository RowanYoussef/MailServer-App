package com.example.demo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Sort {
    boolean order;

    public Sort(boolean order) {
        this.order = order;
    }

    public boolean whichOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
    private static ArrayList<String> priority=new ArrayList<>(
            Arrays.asList("soImportant","Important","Default","Low")
    );
    
    public ArrayList<Email> sort(Profile p, String whatTOsort, String sortPolicy){
    	switch(whatTOsort) {
    	case "Inbox" :
    		 return SortMails(p.getInbox().getEmails(),sortPolicy);
    		 
    	case "Trash" :
   		 return SortMails(p.getTrash().getTrashemails(),sortPolicy);
   		 
    	case "Draft" :
      		 return SortMails(p.getDraft().getDraftemails(),sortPolicy);
      		 
    	case "Sent" :
    		 return SortMails(p.getSent().getSentemails(),sortPolicy);
    	}
    	return null;
    	
    }

    public ArrayList<Email> SortMails(ArrayList<Email> E, String SortPolicy)  {
        ArrayList<Email> Res = new ArrayList<>();
        switch (SortPolicy)
        {
            case "Subject":
            {
                Res=this.sortBysubject(E);
                break;
            }
            case "Date":
            {
                Res=this.sortBydate(E);
                break;
            }
            case "Sender":
            {
                Res=this.sortBySender(E);
                break;
            }
            case "Receiver":
            {
                Res=this.sortByReciever(E);
                break;
            }
            case "Body":
            {
                Res=this.sortByBody(E);
                break;
            }
            case "Priority":
            {
                Res=this.sortByPriority(E);
                break;
            }
          
        }
        return Res;
    }

    private ArrayList<Email> sortBydate(ArrayList<Email> E){
        Email[] sorted=E.toArray(Email[]::new);
        Arrays.sort(sorted, (E1, E2) -> {
   		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
   		  LocalDate a = LocalDate.parse(E1.getDate(), dtf);
   		  LocalDate b = LocalDate.parse(E2.getDate(), dtf);
   		  if(a.getYear()>b.getYear())
   			  return 1;
   		  if(a.getYear()==b.getYear()) {
   			  if(a.getMonthValue()>b.getMonthValue())
   				  return 1;
   			  if(a.getMonthValue()==b.getMonthValue()) {
   				  if(a.getDayOfMonth()>=b.getDayOfMonth())
   					  return 1;
   			  }  
   		  }
   		   return -1;
   	          }
        );
        ArrayList<Email> sortedlist=new ArrayList<>(Arrays.asList(sorted));
        if(!this.order)
        {
            Collections.reverse(sortedlist);
        }
        return sortedlist;
    }
    private ArrayList<Email> sortBysubject(ArrayList<Email> E){
        Email[] sorted=E.toArray(Email[]::new);
        Arrays.sort(sorted, Comparator.comparing(email -> email.getSubject().toLowerCase()));
        ArrayList<Email> sortedlist=new ArrayList<>(Arrays.asList(sorted));

        if(!this.order)
        {
            Collections.reverse(sortedlist);
        }
        return sortedlist;

    }

    private ArrayList<Email> sortBySender(ArrayList<Email> E){
        Email[] sorted=E.toArray(Email[]::new);
        Arrays.sort(sorted,Comparator.comparing(email -> email.getSender().toLowerCase()));
        ArrayList<Email> sortedlist=new ArrayList<>(Arrays.asList(sorted));
        if(!this.order)
        {
            Collections.reverse(sortedlist);
        }
        return sortedlist;
    }
    private ArrayList<Email> sortByReciever(ArrayList<Email> E){
        Email[] sorted=E.toArray(Email[]::new);
        Arrays.sort(sorted, Comparator.comparing(email -> email.getReceiver().toLowerCase()));
        ArrayList<Email> sortedlist=new ArrayList<>(Arrays.asList(sorted));
        if(!this.order)
        {
            Collections.reverse(sortedlist);
        }
        return sortedlist;
    }
    private ArrayList<Email> sortByBody(ArrayList<Email> E){
        Email[] sorted=E.toArray(Email[]::new);
        Arrays.sort(sorted,Comparator.comparing(email -> email.getBody().toLowerCase()));
        ArrayList<Email> sortedlist=new ArrayList<>(Arrays.asList(sorted));
        if(!this.order)
        {
            Collections.reverse(sortedlist);
        }
        return sortedlist;
    }
    private ArrayList<Email> sortByPriority(ArrayList<Email> E){
        Email[] sorted=E.toArray(Email[]::new);
        Arrays.sort(sorted, (first, second) -> {
            if(priority.indexOf(first.getPriority()) < priority.indexOf(second.getPriority())){
                return 1;
            }
            else{
                return -1;
            }
        });
        ArrayList<Email> sortedlist=new ArrayList<>(Arrays.asList(sorted));
        if(!this.order)
        {
            Collections.reverse(sortedlist);
        }
        return sortedlist;
    }
}
