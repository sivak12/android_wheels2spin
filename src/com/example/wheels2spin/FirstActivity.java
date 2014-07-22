package com.example.wheels2spin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity implements OnClickListener{
	
	
private Button btnLease,btnRent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity);
		
		btnLease   		  =   ( Button)findViewById(R.id.btnLease);
		btnRent   =   (Button)findViewById(R.id.btnRent);
				
		btnLease.setOnClickListener(this);
		btnRent.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View view) 
	{
		Intent intent = null;
		switch(view.getId())
		{
			case R.id.btnLease:
				intent = new Intent(FirstActivity.this,HomeActivity.class);
				intent.putExtra("type",1);
			break;
			case R.id.btnRent:
				intent = new Intent(FirstActivity.this,HomeActivity.class);
				intent.putExtra("type",2);
				break;
		}
		if(intent!=null)
			startActivity(intent);
	}


}
