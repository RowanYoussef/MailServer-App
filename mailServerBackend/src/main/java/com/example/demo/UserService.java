package com.example.demo;
import org.hibernate.query.ReturnableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream; 

@Service
public class UserService implements UserServiceIF {
	@Autowired
	private UserRepository userRepository;
	
	
public String signUp (SignUp signup)
{		
		//check validity of email address
		if(!isValidEmail(signup.getEmail())) return "invalid email address";
		
		//check if someone username and email is available
		User user = userRepository.findByUsername(signup.getUsername());
		if(user!=null) return "user name is already taken";
	     user =userRepository.findByEmail(signup.getEmail());
		if(user!=null) return "email is used";
		
		else {
			user =new User();
			//check if password equals password cofirmation
			if(!signup.getPassword().equals(signup.getPassConfirm()))return "pass!=confirm";
				
			//check if length of username is valid
			if(signup.getUsername().length()<6) return "too short username";
			else if (signup.getUsername().length()>32) return "too long username";
			
			//check if length of first Name is valid
			if(signup.getFirstname().length()<2) return "too short fist name";
			else if (signup.getFirstname().length()>32) return "too long first name";
			
			//check if length of last Name is valid
			if(signup.getLastname().length()<2) return "too short last name";
			else if (signup.getFirstname().length()>32) return "too long last name";
			
			//check if length of password is valid
			if(signup.getPassword().length()<8)return "too short password";
			else if (signup.getPassword().length()>64) return "too long password";
			
			//check if username is valid or not
			if(signup.getUsername().contains(" ")) 
				return "username cannot contain spaces";
			
			user.setEmail(signup.getEmail());
			user.setPassword(signup.getPassword());
			user.setUsername(signup.getUsername());
			user.setFirstname(signup.getFirstname());
			user.setLastname(signup.getLastname());

			userRepository.save(user);
			return "signUpSuccess";
		}
}
	
public String logIn (LogIn login)
{
		User user = userRepository
				            .findByUsernameOrEmail(login.getUsernameOrEmail(),
		                                          login.getUsernameOrEmail());
		if(user==null) {
			return "not exist";
		}
		else
			if(login.getPassword().equals(user.getPassword()))
			return "loginSuccess";
			else return "wrongPassword";
}

public static boolean isValidEmail(String email) 
{ 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
} 

public String Send(Email email)
{ 
	 ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(email.getSender());
	  if(p.isValid(email.getReceiver())) {
		  P.getSent().addSent(email);
		  P = p.getProfile(email.getReceiver());
		  P.getInbox().addEmail(email);
		  return "Success";
	  }
	  return "Failed";
	
	
}
public String SendWithattachment(String Email,MultipartFile file)
     
{ 
	ObjectMapper map = new ObjectMapper();
    Email email = new Email();
	try {
		email = map.readValue(Email, Email.class);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	  Attachment a = new Attachment();
	  a.setFilename(file.getOriginalFilename());
        a.setContentType(file.getContentType());
        try {
			a.setAttachment(file.getBytes());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
        
	 ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(email.getSender());
	  for(Email e : P.getSent().getSentemails()) {
		  if(e.getId().equals(email.getId())) {
			  P=p.getProfile(e.getReceiver());
			  e.getAttachments().add(a);
			  for(Email e1: P.getInbox().getEmails()) {
				  if(e1.getId().equals(e.getId())) {
					  e1.getAttachments().add(a);
				  }
			  } 
			  return "Success";
		  }
	  }

	  if(p.isValid(email.getReceiver())) {
			  email.getAttachments().add(a);
			  P.getSent().addSent(email);
			  P = p.getProfile(email.getReceiver());
			  P.getInbox().addEmail(email);
			 
			  return "Success";
		  
	  }
	return "Failed";
	
}
public String Draft(Email email)
{ 
	 ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(email.getSender());
	  for(Email e: P.getDraft().getDraftemails()) {
		  if(e.getId().equals(email.getId())) {
			  P.getDraft().removefromDraft(email.getId());
		  }
	  }
	  P.getDraft().addToDraft(email);

		  return "Success";
	  
	
	
}
public int getNumOfstates(String username) {
	ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return (int) Math.ceil(P.getInbox().getEmails().size()/3.0-1);
}
public ArrayList<Email> getForWardOrBackWard(int state,String username){
	 ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getInbox().getForWardOrBackWard(state);
}

public ArrayList<Email> OpenInbox(String username)
{ 
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getInbox().getEmails();
}

public ArrayList<Email> OpenSent(String username)
{ 
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getSent().getSentemails();
	
}

public ArrayList<Email> OpenTrash( String username)
{ 
	ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getTrash().getTrashemails();
	
}

public ArrayList<Email> OpenDraft( String username)
{ 
	ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getDraft().getDraftemails();
}


  public void removeFromInbox(String id,String username) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getTrash().moveToTrash(P.getInbox().removeEmail(id));
       
    }

  public void removeFromDraft(String id,String username) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getDraft().removefromDraft(id);
       
    }

  public ArrayList<Email> sort( String whatTOsort, String username
		 ,  String topic,String order){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return new Sort(Boolean.parseBoolean(order)).sort(P, whatTOsort, topic);	
  }

  public ArrayList<Email> search( String whatTOsearch, String username
		 ,  String topic,String key){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return new SearchEmails().search(P, key, whatTOsearch, topic);
  }
  

  public ArrayList<Email> filter( String whatTOfilter, String username
		 , String filterkey, String input){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return new FilterCriteria(input).Filter(P, whatTOfilter, filterkey);
  }
  
 
  public void  Restore(String username, String id){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getInbox().addEmail(P.getTrash().Restore(id));
	  
  }
 
  public void Addcontact(String username,String contactname) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getContacts().addContact(contactname);
	  
	  
  }
 
  public  ArrayList<Contact> Getcontact(String username) {
  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getContacts().getContacts();
	  
  }
  public void CreateFolder(String foldername, String username) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getFolders().add(new Folder(foldername));
  }
  public ArrayList<Folder> GetFolders(String username){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getFolders();
  }
  public void AddEmailToFolder(String id, String username, String foldername) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	   P.getFolderbyName(foldername).addEmail(P.getInbox().removeEmail(id));
	  
  }
  public ArrayList<Email> getFolderEmails(String username, String foldername){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  return P.getFolderbyName(foldername).getEmails();
  }
  public void DeleteFolderEmail(String id,String username,String foldername) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getTrash().getTrashemails().add(P.getFolderbyName(foldername).DeleteFolderEmail(id));
  }
  public void DeleteFolder(String foldername,String username) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.DeleteFolder(foldername);
  }
  public void RenameFolder(String username,String oldfoldername, String newfoldername) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getFolderbyName(oldfoldername).setFoldername(newfoldername);
  }
 
  public ArrayList<Email> getContactEmails(String username,
		  String contactname){
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  ArrayList <Email> conc = new ArrayList<>();	
      conc =(ArrayList<Email>) Stream.concat(P.getInbox().getEmails().stream(),
            		P.getSent().getSentemails().stream())
            .collect(Collectors.toList()); 
	  return new FilterCriteria(contactname).meetCriteria(conc, "Receiver");
	  
  }
  public void DeleteContact(String conName,String username) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getContacts().deleteContact(conName);
  }
  public void AddusernameToContact(String username,String conusername,String conname) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getContacts().GetcontactByName(conname).addusername(conusername,p.getProfiles());
  }
  public ArrayList<Contact> searchContacts(String username,String conname) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  SearchAndSortContacts sc = new SearchAndSortContacts ();
	  return sc.SearchContact(conname, P.getContacts().getContacts());
  }
  public ArrayList<Contact> sortContacts(String username) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  SearchAndSortContacts sc = new SearchAndSortContacts ();
	  return sc.SortContacts(P.getContacts().getContacts());
  }
  public void RenameContact(String username,String oldconname,String newconname) {
	  ProfilesDatabase p= ProfilesDatabase.getInstance();
	  Profile P = p.getProfile(username);
	  P.getContacts().GetcontactByName(oldconname).setName(newconname);
  }
  

public void NewProfile(SignUp sign) {
	ProfilesDatabase data = ProfilesDatabase.getInstance();
	data.addProfile(new Profile(sign.getFirstname(),sign.getLastname(),sign.getUsername(),sign.getPassword(),sign.getEmail()));
	
}
}
