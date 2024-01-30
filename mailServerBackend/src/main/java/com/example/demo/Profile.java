package com.example.demo;

import java.util.ArrayList;

public class Profile extends User {
	private Inbox inbox;
	private Trash trash;
	private Draft draft;
	private Sent sent;
	private  ContactsProfile contacts ;
	private ArrayList<Folder> folders;

	
	public Profile(String Firstname,String Lastname,String Username,String Password,String Email) {
		super.setEmail(Email);
		super.setFirstname(Firstname);
		super.setLastname(Lastname);
		super.setPassword(Password);
		super.setUsername(Username);
		this.inbox = new Inbox();
		this.trash = new Trash();
		this.draft = new Draft();
		this.sent = new Sent();
		this.contacts= new ContactsProfile();
		this.folders= new ArrayList();
	}
   

	public ArrayList<Folder> getFolders() {
		return folders;
	}


	public void setFolders(ArrayList<Folder> folders) {
		this.folders = folders;
	}


	public ContactsProfile getContacts() {
		return contacts;
	}

	public void setContacts(ContactsProfile contacts) {
		this.contacts = contacts;
	}

	public Inbox getInbox() {
		return inbox;
	}
	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}
	public Trash getTrash() {
		return trash;
	}
	public void setTrash(Trash trash) {
		this.trash = trash;
	}
	public Draft getDraft() {
		return draft;
	}
	public void setDraft(Draft draft) {
		this.draft = draft;
	}
	public Sent getSent() {
		return sent;
	}
	public void setSent(Sent sent) {
		this.sent = sent;
	}
	public Folder getFolderbyName(String foldername) {
		for(Folder f: folders) {
			if(f.getFoldername().equals(foldername))
				return f;
		}
	return null;
	}
	public void DeleteFolder(String foldername) {
		for(Folder f: folders) {
			if(f.getFoldername().equals(foldername)) {
				folders.remove(f);
			}
	}
	}
	
	
}
