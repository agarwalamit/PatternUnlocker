package com.example.test3;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Adapter extends ArrayAdapter<DotEntity>{
	Context context;	
	ArrayList<DotEntity> mylist;
	int checkedArray[] = new int[100];
	
	int layout;
	DotEntity d;

	public Adapter(Context c,int res,ArrayList<DotEntity> l){
		super(c,res,l);
		context=c;
		layout=res;
		mylist=l;
	}

	@Override
	public View getView(final int position,View convertView,final ViewGroup parent) {	

		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(layout, parent, false);
		}		
		RadioGroup rg = (RadioGroup)view.findViewById(R.id.radioSelection);
		
		
		
		if(checkedArray[position]!=0){
			rg.check(checkedArray[position]);
		}
		
		
		
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				checkedArray[position] = checkedId;

			}
		});



		return view;
	}
}

