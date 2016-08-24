package com.example.blockcall;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;




public class UserSessionManager {
	
	
	SharedPreferences pref;
	

	Editor editor;
	

	Context _context;
	
	
	int PRIVATE_MODE = 0;
	
	
	private static final String PREFER_NAME = "BLOCKCALL";
	
	// All Shared Preferences Keys
	private static final String IS_USER_LOGIN = "IsUserLoggedIn";
	
	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";
	
	// Email address (make variable public to access from outside)
	public static final String KEY_PASSWORD = "password";
	
	// Constructor
	public UserSessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	//Create login session
	public void createUserLoginSession(String name, String password){
		// Storing login value as TRUE
		editor.putBoolean(IS_USER_LOGIN, true);
		
		// Storing name in pref
		editor.putString(KEY_NAME, name);
		
		// Storing email in pref
		editor.putString(KEY_PASSWORD, password);
		
		// commit changes
		editor.commit();
	}	
	
	/**
	 * Check login method will check user login status
	 * If false it will redirect user to login page
	 * Else do anything
	 * */
	public boolean checkLogin(){
		// Check login status
		if(!this.isUserLoggedIn()){
			
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, Home.class);
			
			// Closing all the Activities from stack
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			// Staring Login Activity
			_context.startActivity(i);
			
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		
		
		HashMap<String, String> user = new HashMap<String, String>();
		

		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		
	
		user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
		
	
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		
		
		editor.clear();
		editor.commit();
		
		
		Intent i = new Intent(_context, LoginActivity.class);
		
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		

		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		_context.startActivity(i);
	}
	
	// Check for login
	public boolean isUserLoggedIn(){
		return pref.getBoolean(IS_USER_LOGIN, false);
	}
}
