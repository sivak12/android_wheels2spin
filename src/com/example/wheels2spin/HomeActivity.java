package com.example.wheels2spin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener
{
	private Button btnRegister,btnStatusRegistered,btnRent,btnStatusRent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		btnRegister   		  =   ( Button)findViewById(R.id.btnRegister);
		btnStatusRegistered   =   (Button)findViewById(R.id.btnStatusRegistered);
		btnRent  			  =   (Button)findViewById(R.id.btnRent);
		btnStatusRent   	  =   (Button)findViewById(R.id.btnStatusRent);
		
				
		
		int type=1;
		if(getIntent().getExtras()!=null)
        	type = getIntent().getExtras().getInt("type");
				
		btnRegister.setOnClickListener(this);
		btnStatusRegistered.setOnClickListener(this);
		btnRent.setOnClickListener(this);
		btnStatusRent.setOnClickListener(this);
		
		if(type == 1) {
			
			btnRent.setVisibility(View.GONE);
			btnStatusRent.setVisibility(View.GONE);					
		}
		else {			
			
			btnRegister.setVisibility(View.GONE);
			btnStatusRegistered.setVisibility(View.GONE);			
		}	
		
	}
	
	
	@Override
	public void onClick(View view) 
	{
		Intent intent = null;
		switch(view.getId())
		{
			case R.id.btnRegister:
				intent = new Intent(HomeActivity.this,BikeRegistration.class);
			break;
			case R.id.btnRent:
				intent = new Intent(HomeActivity.this,BikeList.class);
				intent.putExtra("flag",0);
				break;
			case R.id.btnStatusRegistered:
				intent = new Intent(HomeActivity.this,BikeList.class);
				intent.putExtra("flag",1);
				break;			
			case R.id.btnStatusRent:
				intent = new Intent(HomeActivity.this,BikeList.class);
				intent.putExtra("flag",2);
				break;
		}
		if(intent!=null)
			startActivity(intent);
	}
}
