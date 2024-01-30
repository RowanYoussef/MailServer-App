package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;

public class Sent {
	ArrayList<Email> Sentemails = new ArrayList<>();

	public ArrayList<Email> getSentemails() {
//		 ArrayList <Email> reversed = (ArrayList<Email>) Sentemails.clone();
//		   Collections.reverse(reversed);
		return this.Sentemails;
	}

	public void setSentemails(ArrayList<Email> sentemails) {
		Sentemails = sentemails;
	}
	
	public void addSent(Email email) {
		this.Sentemails.add(email);
	}
	public void DeleteSent(Email email) {
		this.Sentemails.remove(email);
	}
	

}
