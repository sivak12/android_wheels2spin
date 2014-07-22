package com.example.wheels2spin.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.wheels2spin.BikeList;
import com.example.wheels2spin.R;

public class BikeListAdapter extends BaseAdapter
{

	private Context mContext;
	private ArrayList<HashMap<String, String>> bikeList;
	private int flag = 0;
	public BikeListAdapter(Context mContext,int flag)
	{
		this.mContext = mContext;
		this.flag     = flag;
	}
	
	@Override
	public int getCount() 
	{
		if(bikeList!=null && bikeList.size()>0)
			return bikeList.size();
		return 0;
	}

	@Override
	public Object getItem(int arg0)
	{
		return arg0;
	}

	@Override
	public long getItemId(int arg0)
	{
		return arg0;
	}
	
	public void refreshList( ArrayList<HashMap<String, String>> bikeList)
	{
		this.bikeList  = (ArrayList<HashMap<String, String>>) bikeList.clone();
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView  = LayoutInflater.from(mContext).inflate(R.layout.bike_list_item,null);
		
		TextView tvOwnerName  		   =  (TextView) convertView.findViewById(R.id.tvOwnerName);
		TextView tvMobileNumber  	   =  (TextView) convertView.findViewById(R.id.tvMobileNumber);
		TextView tvModelNumber		   =  (TextView) convertView.findViewById(R.id.tvModelNumber);
		TextView tvRegistrationNumber  =  (TextView) convertView.findViewById(R.id.tvRegistrationNumber);
		
		Button btnAccept               =  (Button)convertView.findViewById(R.id.btnAccept);
		Button btnReject               =  (Button)convertView.findViewById(R.id.btnReject);
		
		if(flag == 1)
		{
			btnAccept.setVisibility(View.VISIBLE);
			btnReject.setVisibility(View.VISIBLE);
			
		}
		else
		{
			btnAccept.setVisibility(View.GONE);
			btnReject.setVisibility(View.GONE);
			
		}	
		HashMap<String,String> hashMap =  bikeList.get(position);
		
		final int bikeID = Integer.parseInt(hashMap.get("bike_id"));
		tvOwnerName.setText("Owner Name: "+hashMap.get("owner_name"));
		tvMobileNumber.setText("MobileNumber:" +hashMap.get("owner_mobile"));
		tvModelNumber.setText("Model: "+hashMap.get("model"));
		tvRegistrationNumber.setText("Registration Number: "+hashMap.get("reg_no"));
		
		btnAccept.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				((BikeList)mContext).onAcceptClicked(bikeID);
			}
		});
		
		btnReject.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				((BikeList)mContext).onRejectClicked(bikeID);
			}
		});
		return convertView;
	}
	
}
