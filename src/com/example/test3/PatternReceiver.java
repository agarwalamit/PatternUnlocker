package com.example.test3;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PatternReceiver extends Activity{
	Adapter listAdapter;
	ArrayList<DotEntity> mylist = new ArrayList<DotEntity>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patternreceiver);
		ListView lv = (ListView)findViewById(R.id.listview);
		int patternLength = 15;
		DotEntity d= new DotEntity();
		d.setIdSelected(2131296258);
		mylist.add(d);
		d= new DotEntity();
		d.setIdSelected(2131296259);
		mylist.add(d);
		
		for(int i=0;i<patternLength;i++){
			mylist.add(new DotEntity());
		}
		listAdapter = new Adapter(this,R.layout.dotselectionelement, mylist);
		lv.setAdapter(listAdapter);


	}
}
