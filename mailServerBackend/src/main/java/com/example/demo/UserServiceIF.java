package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public interface UserServiceIF {
	public String signUp (SignUp signup);
	public String logIn (LogIn login);
	public void NewProfile(SignUp sign);
	public String Send(Email email);
	public ArrayList<Email> OpenInbox(String username);
	public ArrayList<Email> OpenSent(String username);
	public ArrayList<Email> OpenTrash(String username);
	public ArrayList<Email> OpenDraft(String username);
	public void removeFromInbox(String id, String username);
	public void removeFromDraft(String id, String username);
	public ArrayList<Email> sort(String whatTOsort, String username, String topic, String order);
	public ArrayList<Email> search(String whatTOsearch, String username, String topic, String key);
	public ArrayList<Email> filter(String whatTOfilter, String username, String filterkey, String input);
	public void Restore(String username, String id);
	public ArrayList<Contact> Getcontact(String username);
	public void Addcontact(String username, String contactname);
	public ArrayList<Email> getContactEmails(String username, String contactname);
	public String Draft(Email email);
	public void CreateFolder(String foldername, String username);
	public ArrayList<Folder> GetFolders(String username);
	public void AddEmailToFolder(String id, String username, String foldername);
	public ArrayList<Email> getFolderEmails(String foldername, String username);
	public void DeleteFolderEmail(String id, String username, String foldername);
	public void DeleteFolder(String foldername, String username);
	public void RenameFolder(String username, String oldfoldername, String newfoldername);
	public void DeleteContact(String conName, String username);
	public void AddusernameToContact(String username, String conusername, String conname);
	public ArrayList<Contact> searchContacts(String username, String conname);
	public ArrayList<Contact> sortContacts(String username);
	public void RenameContact(String username, String oldconname, String newconname);
	public ArrayList<Email> getForWardOrBackWard(int state, String username);
	public int getNumOfstates(String username);
	public String SendWithattachment(String email, MultipartFile file);
	
}
