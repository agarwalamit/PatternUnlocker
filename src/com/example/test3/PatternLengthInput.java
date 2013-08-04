package com.example.test3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PatternLengthInput extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patternlengthinput);
		EditText edit = (EditText)findViewById(R.id.patternlength);
		String patternLength = edit.getText().toString();
		
	}
	public void onClickOk(View v){
		startActivity(new Intent(this,PatternReceiver.class));
		
	}
	

}
