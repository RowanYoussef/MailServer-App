package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SearchAndSortContacts {

    public ArrayList<Contact> SearchContact(String name,ArrayList<Contact> contacts){
    	ArrayList<Contact> searched = new ArrayList<>();
    	for(Contact c: contacts) {
    		if(c.getName().toLowerCase().contains(name.toLowerCase())) {
    			searched.add(c);
    		}
    			
    	}
    	return searched;
    }
    
    public ArrayList<Contact> SortContacts(ArrayList<Contact> contacts){
    	 Contact[] sorted = contacts.toArray(Contact[]::new);
         Arrays.sort(sorted,Comparator.comparing(contact -> contact.getName().toLowerCase()));
         return new ArrayList<>(Arrays.asList(sorted));
    }
    
    
}
