package com.example.demo;

import java.util.ArrayList;

public class ProfilesDatabase {
	 private static final ProfilesDatabase obj = new ProfilesDatabase();
	 private ArrayList<Profile> profiles;
	 
	 public void addProfile(Profile p) {
		 this.profiles.add(p);
	 }
	 
	 public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}

	public Profile getProfile(String username) {
			for(Profile p: profiles) {
				if(p.getUsername().equals(username)) {
	                return p;
				}
	 }
			return null;
	 }
	 
	    private ProfilesDatabase() {
	    	profiles = new ArrayList<>();
	    }
	    
	    public boolean isValid(String username) {
	    	for(Profile p: profiles) {
				if(p.getUsername().equals(username)) {
	                return true;
				}
	    	
	    }
	    	return false;
	    }
	 
	    public static ProfilesDatabase getInstance()
	    {
	       /* if (obj==null)
	            obj = new ProfilesDatabase();*/
	        return obj;
}
}
