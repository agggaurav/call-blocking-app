package com.example.blockcall;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

//UserSessionManager	session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Button btnlogin=(Button)findViewById(R.id.login);
		Button btnsignup=(Button)findViewById(R.id.signup);
	//	session=new UserSessionManager(getApplicationContext());
		
		
		
		btnlogin.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
			Intent intent=new Intent(getApplicationContext(),LoginActivity.class);	
				startActivity(intent);
			}
		});
     btnsignup.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
			Intent intent=new Intent(getApplicationContext(),SignUp.class);	
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
