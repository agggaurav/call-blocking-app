package com.example.blockcall;

import java.util.HashMap;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Logout extends Activity {


	// User Session Manager Class
	UserSessionManager session;
	
	// Button Logout
	Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        
        // Session class instance
        session = new UserSessionManager(getApplicationContext());
        
        TextView lblName = (TextView) findViewById(R.id.lblName);
       
        
        // Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);
        
        Toast.makeText(getApplicationContext(), 
        		       "User Login Status: " + session.isUserLoggedIn(), 
        		       Toast.LENGTH_LONG).show();
        
        
        
        // Check user login
        // If User is not logged in , This will redirect user to LoginActivity.
      //  if(session.checkLogin())
        //	finish();
        
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        
        // get name
        String name = user.get(UserSessionManager.KEY_NAME);
        
        // get email
        String password = user.get(UserSessionManager.KEY_PASSWORD);
        
        // Show user data on activity
        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
     
        
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// Clear the User session data
				// and redirect user to LoginActivity
				session.logoutUser();
				Intent i=new Intent(getApplicationContext(),Home.class);
				startActivity(i);
				//finish();
			}
		});
    }
        
}
