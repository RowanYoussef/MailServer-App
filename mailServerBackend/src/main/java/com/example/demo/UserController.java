package com.example.demo;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	UserServiceIF userservice;
	ArrayList<Profile> profiles = new ArrayList<>();
	@PostMapping("/signup")
    @ResponseBody
	public String signUP(@RequestBody SignUp sign )
	{
		userservice.NewProfile(sign);
		return userservice.signUp(sign);
	}
	@PostMapping("/login")
    @ResponseBody
	public String logIn (@RequestBody LogIn login)
	{
		return userservice.logIn(login);
	}
	@PostMapping("/Send")
    @ResponseBody
	public String Send(@RequestBody Email email)
	{ 
	  return userservice.Send(email);
		
	}
	@PostMapping("/Draft")
    @ResponseBody
	public String Draft(@RequestBody Email email)
	{ 
	  return userservice.Draft(email);
		
	}
	@GetMapping("/Inbox/{username}")
	public ArrayList<Email> OpenInbox(@PathVariable("username") String username)
	{ 
		return userservice.OpenInbox(username);
	}
	@GetMapping("/Sent/{username}")
	public ArrayList<Email> OpenSent(@PathVariable("username") String username)
	{ 
		return userservice.OpenSent(username);
		
	}
	@GetMapping("/Trash/{username}")
	public ArrayList<Email> OpenTrash(@PathVariable("username") String username)
	{ 
		return userservice.OpenTrash(username);
		
	}
	@GetMapping("/Draft/{username}")
	public ArrayList<Email> OpenDraft(@PathVariable("username") String username)
	{ 
		return userservice.OpenDraft(username);
	}
	
	  @DeleteMapping("/remove/{id}/{username}")
	  public void removeFromInbox(@PathVariable ("id")String id,@PathVariable("username")String username) {
		   userservice.removeFromInbox(id,username);
	       
	    }
	  @DeleteMapping("/removeDraft/{id}/{username}")
	  public void removeFromDraft(@PathVariable ("id")String id,@PathVariable("username")String username) {
		  userservice.removeFromDraft(id,username);
	       
	    }
	  @GetMapping("/Sort/{whatTOsort}/{username}/{topic}/{order}")
	  public ArrayList<Email> sort(@PathVariable("whatTOsort") String whatTOsort,@PathVariable("username") String username
			 , @PathVariable("topic") String topic,@PathVariable("order")String order){
		  return  userservice.sort(whatTOsort,username,topic,order);	
	  }
	  @GetMapping("/Search/{whatTOsearch}/{username}/{key}/{topic}")
	  public ArrayList<Email> search(@PathVariable("whatTOsearch") String whatTOsearch,@PathVariable("username") String username
			 , @PathVariable("topic") String topic,@PathVariable("key")String key){
		  return  userservice.search(whatTOsearch,username,topic,key);	
	  }
	  
	  @GetMapping("/Filter/{whatTOfilter}/{username}/{filterkey}/{input}")
	  public ArrayList<Email> filter(@PathVariable("whatTOfilter") String whatTOfilter,@PathVariable("username") String username
			 , @PathVariable("filterkey")String filterkey,@PathVariable("input") String input){
		  return  userservice.filter(whatTOfilter,username,filterkey,input);
	  }
	  
	  @GetMapping("/Restore/{username}/{id}")
	  public void  Restore(@PathVariable("username")String username,@PathVariable("id") String id){
		 userservice.Restore(username,id);
		  
	  }
	  @GetMapping("/AddContact/{username}/{contactname}")
	  public void Addcontact(@PathVariable("username")String username,@PathVariable("contactname")String contactname) {
		
		  userservice.Addcontact(username,contactname);
		  
	  }
	  @GetMapping("/getContact/{username}")
	  public  ArrayList<Contact> Getcontact(@PathVariable("username")String username) {
		  return userservice.Getcontact(username);
		  
	  }
	  @GetMapping("getContactEmails/{username}/{contactname}")
	  public ArrayList<Email> getContactEmails(@PathVariable("username")String username,
			  @PathVariable("contactname")String contactname){
	    return userservice.getContactEmails(username,contactname);
		  
	  }
	  @GetMapping("CreateFolder/{username}/{foldername}")
	  public void CreateFolder(@PathVariable ("foldername")String foldername,@PathVariable("username") String username) {
		  userservice.CreateFolder(foldername,username);
	  }
	  @GetMapping("getFolder/{username}")
	  public ArrayList<Folder> GetFolders(@PathVariable("username") String username){
		  return userservice.GetFolders(username);
	  }
	  @GetMapping("AddToFolder/{id}/{username}/{foldername}")
	  public void AddEmailToFolder(@PathVariable String id,@PathVariable("username") String username, @PathVariable ("foldername")String foldername) {
		  userservice.AddEmailToFolder(id,username,foldername);
	  }
	  @GetMapping("GetFolderEmails/{username}/{foldername}")
	  public ArrayList<Email> getFolderEmails(@PathVariable ("foldername")String foldername,@PathVariable("username") String username){
		  return userservice.getFolderEmails(username,foldername);
		  
	  }
	  @DeleteMapping("DeleteFolderEmail/{username}/{foldername}/{id}")
	  public void DeleteFolderEmail(@PathVariable("id")String id,@PathVariable("username")String username,
			  @PathVariable ("foldername")String foldername) {
		  userservice.DeleteFolderEmail(id,username,foldername);
	  }
	  @DeleteMapping("DeleteFolder/{username}/{foldername}")
	  public void DeleteFolder(@PathVariable ("foldername")String foldername,@PathVariable("username")String username) {
		  userservice.DeleteFolder(foldername,username);
	  }
	  @GetMapping("RenameFolder/{username}/{oldfoldername}/{newfoldername}")
	  public void RenameFolder(@PathVariable("username")String username,@PathVariable ("oldfoldername")String oldfoldername,
			  @PathVariable ("newfoldername")String newfoldername) {
		  userservice.RenameFolder(username,oldfoldername,newfoldername);
		  
	  }
	  @DeleteMapping("DeleteContact/{username}/{conname}")
	  public void DeleteContact(@PathVariable("conname")String conName,@PathVariable("username")String username) {
		  userservice.DeleteContact(conName,username);
	  }
	  @GetMapping("AddUsernameTocontact/{username}/{conname}/{conusername}")
	  public void AddusernameToContact(@PathVariable("username")String username,
			  @PathVariable("conusername")String conusername,@PathVariable("conname")String conname) {
		  userservice.AddusernameToContact(username,conusername,conname);
	  }
	  @GetMapping("SearchContacts/{username}/{conname}")
	  public ArrayList<Contact> searchContacts(@PathVariable("username")String username,@PathVariable("conname")String conname){
		  return userservice.searchContacts(username,conname);
		  
	  }
	  @GetMapping("SortContacts/{username}")
	  public ArrayList<Contact> sortContacts(@PathVariable("username")String username){
		  return userservice.sortContacts(username);
		  
	  }
	  @GetMapping("RenameContact/{username}/{oldconname}/{newconname}")
	  public void RenameContact(@PathVariable("username")String username,@PathVariable("oldconname")String oldconname,
			  @PathVariable("newconname")String newconname) {
		    userservice.RenameContact(username,oldconname,newconname);
	  }
	  @GetMapping("GetForWardOrBackWard/{username}/{state}")
	  public ArrayList<Email> getForWardOrBackWard(@PathVariable ("state")int state,
			  @PathVariable("username")String username){
		  return userservice.getForWardOrBackWard(state,username);
	  }
	  @GetMapping("GetNumOfstates/{username}")
	  public int getNumOfstates(@PathVariable ("username")String username) {
			return userservice.getNumOfstates(username);
		}
	  @PostMapping("/SendWithAttachment")
	  public String SendWithattachment(@RequestParam("email") String email,@RequestParam("file") MultipartFile file) {
		  return userservice.SendWithattachment(email,file);
	  }
	  
}
