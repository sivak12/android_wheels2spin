package com.example.wheels2spin;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BikeRegistration extends Activity{
	EditText owner_name,owner_mobile,model,reg_no;
	DBController controller = new DBController(this);
	private AlertDialog.Builder alertDialog;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.bike_registration);
	        owner_name = (EditText) findViewById(R.id.owner_name);
	        owner_mobile = (EditText) findViewById(R.id.owner_mobile);
	        model = (EditText) findViewById(R.id.model);
	        reg_no = (EditText) findViewById(R.id.reg_no);
	        
	        alertDialog             =   new AlertDialog.Builder(BikeRegistration.this);
	    }
	public void registerBike(View view) {
		
		
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("owner_name", owner_name.getText().toString());
		queryValues.put("owner_mobile", owner_mobile.getText().toString());
		queryValues.put("model", model.getText().toString());
		queryValues.put("reg_no", reg_no.getText().toString());
		
		
		/*
		this.callHomeActivity(view);
		
		
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("model", model.getText().toString());
		queryValues.put("reg_no", "abcd123");
		queryValues.put("owner_name", "sam");
		queryValues.put("owner_mobile", 121223);
		queryValues.put("model", reason.getText().toString());
		queryValues.put("reg_no", amount.getText().toString());
		
		
		query = "CREATE TABLE bikes ( bike_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"model TEXT, " +
				"reg_no TEXT,"+
				"owner_name TEXT" +
				"owner_mobile INTEGER? " +
				"available TEXT)";
		
		*/
		
		
		
		
		controller.insertBike(queryValues);
		showCoustomDialog("Your Bike Is Registered");
	}
	
	public void showCoustomDialog(String message)
	{
		if (alertDialog != null) 
		{
			alertDialog.setTitle("Alert");
			alertDialog.setMessage(message);
			alertDialog.setCancelable(false);
			alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() 
			{
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					dialog.dismiss();
					finish();
				}
			});
			alertDialog.show();
		}
	}
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		finish();
	}
	
	

}
