package com.example.wheels2spin;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.wheels2spin.adapter.BikeListAdapter;

public class BikeList extends Activity {
	
	private DBController controller = new DBController(this);
	private ArrayList<HashMap<String, String>> bikesList;
	private BikeListAdapter bikeListAdapter;
	private ListView lvBikeList;
	private AlertDialog.Builder alertDialog;
	int flag = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_list);
        if(getIntent().getExtras()!=null)
        	flag = getIntent().getExtras().getInt("flag");
        
        lvBikeList       =  (ListView)findViewById(R.id.lvBikeList);
        alertDialog      =   new AlertDialog.Builder(BikeList.this);
        bikeListAdapter  =   new BikeListAdapter(BikeList.this,flag);
        lvBikeList.setAdapter(bikeListAdapter);
        
        
        new Thread(new Runnable()
        {
			
			@Override
			public void run() 
			{
				if(flag == 0)
					bikesList  =  controller.getAllRentBikes();
				else if(flag == 1)
					bikesList  =  controller.getAllRegisteredBikes();
				else if(flag == 2)
					bikesList  =  controller.getAllRentAcceptedBikes();
				
				runOnUiThread(new Runnable() 
				{
					
					@Override
					public void run() 
					{
						bikeListAdapter.refreshList(bikesList);
					}
				});
			}
		}).start();
        
        lvBikeList.setOnItemClickListener(new OnItemClickListener() 
        {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,final int position,long positionLong) 
			{
				new Thread(new Runnable()
				{
					
					@Override
					public void run()
					{
						if(flag == 0)
						{
							int bikeID = Integer.parseInt(bikesList.get(position).get("bike_id"));
							controller.updateStatus(bikeID,1);
							runOnUiThread(new Runnable() 
							{
								
								@Override
								public void run() 
								{
									showCoustomDialog("Bike Request Sent To The Owner");
								}
							});
						}
						else if(flag == 2)
						{
							runOnUiThread(new Runnable() 
							{
								
								@Override
								public void run() 
								{
									Toast.makeText(BikeList.this,"In Progress",Toast.LENGTH_LONG).show();
								}
							});
						}
						
						
					}
				}).start();
			
				
			}
		});
        
  
        
    }
	
	@Override
	public void onBackPressed() 
	{
		super.onBackPressed();
		finish();
	}
	 
	 public void bookBike(View view) 
	 {
			System.out.println("Booking Bike !!!!!!!!!!!!1");
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
	
	public void onAcceptClicked(int bikeID)
	{
		controller.updateStatus(bikeID,2);
		runOnUiThread(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				showCoustomDialog("Bike Accepted");
			}
		});
	}
	
	public void onRejectClicked(int bikeID)
	{
		controller.updateStatus(bikeID,0);
		runOnUiThread(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				showCoustomDialog("Bike Rejected");
			}
		});
	}
	 

}
