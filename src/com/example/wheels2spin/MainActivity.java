package com.example.wheels2spin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity
{

	DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    
    public void registerBike(View view) {
		Intent objIntent = new Intent(getApplicationContext(), BikeRegistration.class);
		startActivity(objIntent);
	}
    
    public void getRegisteredBikeStatus(View view) {
		Intent objIntent = new Intent(getApplicationContext(), BikeList.class);
		startActivity(objIntent);
	}
    
    public void rentBike(View view) {
		Intent objIntent = new Intent(getApplicationContext(), BikeList.class);
		startActivity(objIntent);
	}
    
    
    public void getRequestedBikeStatus(View view) {
		Intent objIntent = new Intent(getApplicationContext(), BikeList.class);
		startActivity(objIntent);
	}
    
    
}
