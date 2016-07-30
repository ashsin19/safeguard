package com.exmple.safeguard;




import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.exmple.besafe.R;


public class MainActivity extends Activity {
	
	SharedPreferences sp;
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		sp= getSharedPreferences("login", MODE_PRIVATE);
		if(sp.getString("number1", null) != null)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_safe_m);
			Intent intent2=new Intent(MainActivity.this,Safe_m.class);
			startActivity(intent2);
			
		}
		else
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			Intent intent3=new Intent(MainActivity.this,Intro.class);
			startActivity(intent3);

		}

	}
}
