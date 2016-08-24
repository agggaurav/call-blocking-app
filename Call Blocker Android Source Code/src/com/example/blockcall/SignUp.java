package com.example.blockcall;







import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUp extends Activity implements OnClickListener{

	EditText Name,Password;
	Button btnCreateAccount;
	UserSessionManager session;
	LoginDatabaseAdapter loginDataBaseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		loginDataBaseAdapter=new LoginDatabaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		 session = new UserSessionManager(getApplicationContext()); 
		Name=(EditText)findViewById(R.id.txtUsername);
		Password=(EditText)findViewById(R.id.txtPassword);
		btnCreateAccount=(Button)findViewById(R.id.btnLogin);
		btnCreateAccount.setOnClickListener(this);
	}
	public void onClick(View args)
	{
		String name=Name.getText().toString();
		String password=Password.getText().toString();
		if(name.equals("")||password.equals(""))
		{
				Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
				return;
		}
		// check if both password matches
		else
		{
		    // Save the Data in Database
			session.createUserLoginSession(name, password);
		    loginDataBaseAdapter.insertEntry(name,password);
		   
		    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
		
		    Intent int2=new Intent(this,Blockers.class);
		    
		    int2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			
			int2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		       startActivity(int2);
		   	
		finish();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	
	}


@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	
	loginDataBaseAdapter.close();
}
}

