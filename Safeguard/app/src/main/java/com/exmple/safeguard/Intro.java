package com.exmple.safeguard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.exmple.besafe.R;

public class Intro extends Activity {
	Button btncontinue;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		btncontinue=(Button) findViewById(R.id.button1);
		btncontinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setContentView(R.layout.activity_main);
				Intent intent4= new Intent(Intro.this,EnterNumbers.class);
				startActivity(intent4);
				
			}
		});
	}

}
