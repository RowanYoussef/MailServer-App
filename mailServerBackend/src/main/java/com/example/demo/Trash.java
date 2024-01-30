package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

import ch.qos.logback.core.util.Duration;

public class Trash {
	 ArrayList<Email> trashemails = new ArrayList<>();
	 ArrayList<LocalDate> deletedDates= new ArrayList<>();

	 
	public void CheckOnDates() {
		LocalDate  localDate = LocalDate.now();
		for(int i=0;i<this.trashemails.size();i++) {
			 LocalDate date2 = (this.deletedDates.get(i));
			 long daysBetween = ChronoUnit.DAYS.between(date2, localDate);
			 if(daysBetween>=30) {
				 this.trashemails.remove(this.trashemails.get(i));
				 this.deletedDates.remove(this.deletedDates.get(i));
			 }
		}
		
	}
	public ArrayList<Email> getTrashemails() {
		//CheckOnDates();
		for(Email e: trashemails) {
			if(e==null)
				trashemails.remove(e);
		}
//		 ArrayList <Email> reversed = (ArrayList<Email>) trashemails.clone();
//		   Collections.reverse(reversed);
		return this.trashemails;
	}

	public void setTrashemails(ArrayList<Email> trashemails) {
		this.trashemails = trashemails;
	}
	 
	public void moveToTrash(Email email) {
		this.deletedDates.add(LocalDate.now());
		this.trashemails.add(email);
	}
	public void removeFromTrash(Email email) {
		this.trashemails.remove(email);
	}
	public Email Restore(String id) {
		for(Email e: trashemails) {
	   if(e.getId().equals(id))
		   trashemails.remove(e);
	       return e;
		}
		return null;
	}
}
