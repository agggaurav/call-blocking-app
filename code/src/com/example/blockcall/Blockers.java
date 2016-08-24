package com.example.blockcall;
 
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Blockers  extends Activity implements RadioGroup.OnCheckedChangeListener, OnClickListener {

	private SharedPreferences myPrefs; 
	private SharedPreferences.Editor editor; 
	private RadioButton allBlock;
	private RadioButton unSaved;
	private RadioButton fromList;
	private RadioButton cancelAll;
	private Button btnAddNumber;
	private Button btnShowList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	
//        ///////////////////////////////////
         myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
         editor=myPrefs.edit();     
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radGroup1);
        radiogroup.setOnCheckedChangeListener(this);
        // radio button setting
        allBlock=(RadioButton) findViewById(R.id.blockAll);
        unSaved=(RadioButton) findViewById(R.id.blockUnsaved);
        fromList=(RadioButton) findViewById(R.id.blockFromList);
        cancelAll=(RadioButton) findViewById(R.id.cancelBlocker);
        setDefaultButtonChecked();
        // add and show button controls
        btnAddNumber=(Button) findViewById(R.id.btnAdd);
        btnShowList=(Button) findViewById(R.id.btnShow);
        btnAddNumber.setOnClickListener(this);
        btnShowList.setOnClickListener(this);
	}
	private  void setDefaultButtonChecked()
	{
		String value=getSharedPreferences();
		if(value.equals("all"))
		{
			allBlock.setChecked(true);
		}
		else if(value.equals("unsaved"))
		{
			 unSaved.setChecked(true);
		}
		else if(value.equals("list"))
		{
			 fromList.setChecked(true);
		}
		else if(value.equals("cancel"))
		{
			 cancelAll.setChecked(true);
		}
		else {
			cancelAll.setChecked(true);
		}
	}
	 public void onCheckedChanged(RadioGroup group, int checkedId) {
	        switch (checkedId) {
	        case R.id.blockAll:
	        	 editor.putString("mode", "all");
	        	 editor.commit();
	          break;

	        case R.id.blockUnsaved:
	        	editor.putString("mode", "unsaved");
	        	 editor.commit();
	         
	          break;

	        case R.id.blockFromList:
	        	editor.putString("mode", "list"); 
	        	 editor.commit();
	         
	          break;
	        case R.id.cancelBlocker:
	        	editor.putString("mode", "cancel"); 
	        	 editor.commit();
	        	 
	          break;
	       
	        }
	      }
	  @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.menu_settings: 
	        	  
	        	  Intent i=new Intent(this, NumberList.class);
	        	  startActivity(i);
	            break;
	            case R.id.menu_show: 
	        	  
	        	  Intent ii=new Intent(this, ListActivity.class);
	        	  startActivity(ii);
	            break;
	            
	            case R.id.menu_logout:
	            	Intent lg=new Intent(this,Logout.class);
	            	startActivity(lg);
	            	break;
	        }
	        return true;
	    }
	   
		private String getSharedPreferences() {
			// TODO Auto-generated method stub
			 myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
			 String value=myPrefs.getString("mode", "not");
			 return value;
		}
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnAdd:
				  Intent i=new Intent(this, NumberList.class);
	        	  startActivity(i);
				break;
            case R.id.btnShow:
            	  Intent ii=new Intent(this, ListActivity.class);
	        	  startActivity(ii);
				break;
			default:
				break;
			}
		}
}
