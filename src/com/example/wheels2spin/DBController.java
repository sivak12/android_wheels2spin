package com.example.wheels2spin;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d(LOGCAT,"Created");
    }
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		
		query = "DROP TABLE IF EXISTS bikes";
		database.execSQL(query);
        //onCreate(database);
		
		/*
		bike_id - Primary Key
		model - varchar
		reg_no - varchar 
		owner_name - varchar
		owner_mobile - number
		available -  y/n		
		*/
		
		
		
		query = "CREATE TABLE bikes ( bike_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"model TEXT," +
				"reg_no TEXT,"+
				"owner_name TEXT," +
				"owner_mobile TEXT," +
				"available TEXT," +
				"status INTEGER)";
        database.execSQL(query);
        

        Log.d(LOGCAT,"moneytrans Created");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS bikes";
		database.execSQL(query);
        onCreate(database);
	}
	
	
	public void insertBike(HashMap<String, String> queryValues) {
		
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("model", queryValues.get("model"));
		values.put("reg_no", queryValues.get("reg_no"));
		values.put("owner_name", queryValues.get("owner_name"));
		values.put("owner_mobile",queryValues.get("owner_mobile") );
		values.put("status",0);
		values.put("available","y");
		database.insert("bikes", null, values);
		database.close();
		
	}
	
	
	public void insertTrans(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("reason", queryValues.get("reason"));
		values.put("amount", queryValues.get("amount"));
		database.insert("moneytrans", null, values);
		database.close();
	}
	
	public ArrayList<HashMap<String, String>> getAllRentBikes() {
		
		System.out.println("inside getAllBikes");
		ArrayList<HashMap<String, String>> bikesList;
		bikesList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT bike_id,model,reg_no,owner_name,owner_mobile FROM bikes WHERE status=0";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    
	    if (cursor.moveToFirst()) {
	       System.out.println("records exist");
	    	do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("bike_id",String.valueOf(cursor.getInt(0)));
	        	map.put("model", cursor.getString(1));
	        	map.put("reg_no", cursor.getString(2));
	        	map.put("owner_name", cursor.getString(3));
	        	map.put("owner_mobile", cursor.getString(4));
	        	bikesList.add(map);
	        } while (cursor.moveToNext());
	    }
	    else {
	    	System.out.println("no records");
	    }
	 
	    // return contact list
	    return bikesList;
		
		
		
		
		
		/*
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM animals";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("animalId", cursor.getString(0));
	        	map.put("animalName", cursor.getString(1));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wordList;
	    */
	}
	
	public ArrayList<HashMap<String, String>> getAllRegisteredBikes()
	{
		
		 SQLiteDatabase database = this.getWritableDatabase();
		 Cursor cursor = null;
		 ArrayList<HashMap<String, String>> bikesList = null;
		 try
		 {
				System.out.println("inside getAllRegisteredBikes");
				
				bikesList 		 = new ArrayList<HashMap<String, String>>();
				String selectQuery = "SELECT  * FROM bikes WHERE status=1";
			   
				cursor = database.rawQuery(selectQuery, null);
			    
			    
			    if (cursor!=null && cursor.moveToFirst()) 
			    {
			       System.out.println("records exist");
			    	
			       do 
			       {
			        	HashMap<String, String> map = new HashMap<String, String>();
			        	map.put("bike_id", String.valueOf(cursor.getInt(0)));
			        	map.put("model", cursor.getString(1));
			        	map.put("reg_no", cursor.getString(2));
			        	map.put("owner_name", cursor.getString(3));
			        	map.put("owner_mobile", cursor.getString(4));
			        	bikesList.add(map);
			        } 
			       while (cursor.moveToNext());
			    }
			    else 
			    {
			    	System.out.println("no records");
			    }
			  
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 if(database!=null && database.isOpen())
				 database.close();
			 if(cursor!=null && !cursor.isClosed())
				 cursor.close();
		 }
	
		  return bikesList;
		
	}
	
	public ArrayList<HashMap<String, String>> getAllRentAcceptedBikes()
	{
		
		 SQLiteDatabase database = this.getWritableDatabase();
		 Cursor cursor = null;
		 ArrayList<HashMap<String, String>> bikesList = null;
		 try
		 {
				System.out.println("inside getAllRegisteredBikes");
				
				bikesList 		 = new ArrayList<HashMap<String, String>>();
				String selectQuery = "SELECT  *FROM bikes WHERE status=2 order by bike_id desc limit 1";
			   
				cursor = database.rawQuery(selectQuery, null);
			    
			    
			    if (cursor!=null && cursor.moveToFirst()) 
			    {
			       System.out.println("records exist");
			    	
			       do 
			       {
			        	HashMap<String, String> map = new HashMap<String, String>();
			        	map.put("bike_id", String.valueOf(cursor.getInt(0)));
			        	map.put("model", cursor.getString(1));
			        	map.put("reg_no", cursor.getString(2));
			        	map.put("owner_name", cursor.getString(3));
			        	map.put("owner_mobile", cursor.getString(4));
			        	bikesList.add(map);
			        } 
			       while (cursor.moveToNext());
			    }
			    else 
			    {
			    	System.out.println("no records");
			    }
			  
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 if(database!=null && database.isOpen())
				 database.close();
			 if(cursor!=null && !cursor.isClosed())
				 cursor.close();
		 }
	
		  return bikesList;
		
	}
	

	public void updateStatus(int bikeID,int status)
	{
		
		SQLiteDatabase database = this.getWritableDatabase();
		try
		{
			System.out.println("inside updateStatus");
		    SQLiteStatement stmt = database.compileStatement("UPDATE bikes set status="+status+" WHERE bike_id="+bikeID);
			stmt.executeUpdateDelete();
			stmt.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(database!=null && database.isOpen())
				 database.close();
		}
		
	}
	
}