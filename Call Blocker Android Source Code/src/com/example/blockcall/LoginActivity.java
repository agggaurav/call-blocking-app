package com.example.blockcall;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	EditText user,password;
	LoginDatabaseAdapter loginDatabaseAdapter;
	UserSessionManager session;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginDatabaseAdapter=new LoginDatabaseAdapter(this);
		loginDatabaseAdapter=loginDatabaseAdapter.open();
		session=new UserSessionManager(getApplicationContext());
		  if(session.checkLogin())
	        	finish();
		Button btn1=(Button)findViewById(R.id.btnLogin);
		
		user=(EditText)findViewById(R.id.txtUsername);
		password=(EditText)findViewById(R.id.txtPassword);
		
		btn1.setOnClickListener(this);
		
	}
	
	public void onClick(View args)
	{
		String temp=null;
		String userid=user.getText().toString();
		String Password=password.getText().toString();
		 temp=loginDatabaseAdapter.getSingleEntry(userid);
		if(temp.equals("NOT EXIST"))
		{
			Toast.makeText(getApplication(),temp,Toast.LENGTH_SHORT).show();
		}
		else if(temp.equals(Password)){ 
	Toast.makeText(getApplicationContext(), "checked ", Toast.LENGTH_LONG).show();
	Intent int2=new Intent(this,Blockers.class);
    startActivity(int2);
	}
	}

	

}
