package com.example.blockcall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDatabaseAdapter 
{
		static final String DATABASE_NAME = "cutomer.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 5;//what is the use of name_column
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"customer"+
              "( " +"name"+" text,"+ "email  text Primary Key,PASSWORD text); ";
		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		
		
		public  LoginDatabaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDatabaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String name,String password)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("name",name);
			
		newValues.put("PASSWORD",password);
			
			// Insert the row into your table
			db.insert("customer", null, newValues);
			  Toast.makeText(context, "Account Successfully Created "+name, Toast.LENGTH_LONG).show();
		}
		public int deleteEntry(String UserName)
		{
			//String id=String.valueOf(ID);
		    String where="email=?";
		    int numberOFEntriesDeleted= db.delete("customer", where, new String[]{UserName}) ;
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSingleEntry(String userName)
		{
			Cursor cursor=db.query("customer", null, " name=?", new String[]{userName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			 //Toast.makeText(context,"->>>"+password, Toast.LENGTH_LONG).show();

			return password;				
		}
		public void  updateEntry(String userName,String password)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("email", userName);
			updatedValues.put("PASSWORD",password);
			
	        String where="email = ?";
		    db.update("email",updatedValues, where, new String[]{userName});			   
		}		
}

