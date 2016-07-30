package com.exmple.safeguard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exmple.besafe.R;


public class EnterNumbers extends Activity {
	Button btn1,btn2,btn3,btn4,btn,buttonPickContact;
	EditText e1,e2,e3,e5;
	Editor ed;
	TextView t1,t2,t3,t4;
	Editor e;
	String a,b,c;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_numbers);
		t1=(TextView) findViewById(R.id.textView1);
		e1=(EditText) findViewById(R.id.editText1);
		e2=(EditText) findViewById(R.id.editText2);
		e3=(EditText) findViewById(R.id.editText3);
		e5=(EditText) findViewById(R.id.editText5);
		btn=(Button) findViewById(R.id.button1);
		buttonPickContact = (Button)findViewById(R.id.pickcontact);
		Button pickcontact = (Button)findViewById(R.id.pickcontact2);
		Button pickcontact1=(Button) findViewById(R.id.pickcontact3);
		pickcontact.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
				startActivityForResult(intent, 2);
			}
		});
		buttonPickContact.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub


				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
				startActivityForResult(intent, 1);
			}});
		pickcontact1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
				startActivityForResult(intent, 3);
			}
		});
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				a=e1.getText().toString();
				b=e2.getText().toString();
				c=e3.getText().toString();
				sp=getSharedPreferences("login", MODE_PRIVATE);
				e=sp.edit();
				if(a.equals("") || b.equals("") || c.equals("") || e5.getText().toString().equals(""))
				{
					Toast.makeText(getApplicationContext(), "ENTER DATA",Toast.LENGTH_SHORT).show();
				}
				else{
				e.putString("number1",a);
				e.putString("number2",b);
				e.putString("number3",c);
				e.putString("To", e5.getText().toString() );
				e.commit();
				Toast.makeText(getApplicationContext(), "THANK YOU FOR ENTERING DATA",Toast.LENGTH_LONG).show();
			    Intent intent2=new Intent(EnterNumbers.this,Safe_m.class);
				startActivity(intent2);
				}
				}
		});							
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == 1){
			if(resultCode == RESULT_OK){
				Uri contactData = data.getData();
	Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
	cursor.moveToFirst();
	String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
	e1.setText(number);
			}
		}
		if(requestCode == 2){
			if(resultCode == RESULT_OK){
				Uri contactData = data.getData();
				Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
				cursor.moveToFirst();
				String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
				e2.setText(number);
			}
		}
		if(requestCode == 3){
			if(resultCode == RESULT_OK){
				Uri contactData = data.getData();
				Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
				cursor.moveToFirst();
				String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
				e3.setText(number);
			}
		}
	}

}
