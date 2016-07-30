package com.exmple.safeguard;

import java.io.ByteArrayOutputStream;

import java.io.File;

import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.exmple.besafe.R;


public class Safe_m extends Activity {
   Button btn1,btn2,btn3,btn4,btn5,btn6;
   SharedPreferences sp;
   int a=0;
   File finalFile;
   Uri tempUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_safe_m);
		sp= getSharedPreferences("login", MODE_PRIVATE);
		btn1=(Button) findViewById(R.id.button1);
		btn2=(Button) findViewById(R.id.button2);
		btn3=(Button) findViewById(R.id.button3);
		btn4=(Button) findViewById(R.id.button4);
		btn5=(Button) findViewById(R.id.button);
		btn6=(Button) findViewById(R.id.button6);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phone1=sp.getString("number1", null);
				String phone2=sp.getString("number2", null);
				String phone3=sp.getString("number3", null);
				
				
				String sms="I AM IN DANGER";

				try {
					
					SmsManager smsManager=SmsManager.getDefault();
					smsManager.sendTextMessage(phone1, null, sms, null, null);
					smsManager.sendTextMessage(phone2, null, sms, null, null);
					smsManager.sendTextMessage(phone3, null, sms, null, null);
					Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_SHORT).show();
					
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), "SMS not Sent", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(a%2==0){
				Intent intent5=new Intent(Safe_m.this,PlayClip.class);
				a++;
				btn2.setText("STOP ALARM");
				startService(intent5);
				}
				else{
					Intent intent5=new Intent(Safe_m.this,PlayClip.class);
					a++;
					btn2.setText("START ALARM");
					stopService(intent5);
				}
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent piy=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(piy, 200);
				String mail2=sp.getString("From",null);

/*String phone1=sp.getString("number1", null);
				String phone2=sp.getString("number2", null);
				String phone3=sp.getString("number3", null);

				try {
					SmsManager piyu=SmsManager.getDefault();
					piyu.sendTextMessage(phone1,null,"An urgent e-mail has been sent to your id\n PLEASE OPEN IT AS SOON AS U CAN ",null, null);
					piyu.sendTextMessage(phone2,null,"An urgent e-mail has been sent to your id\n PLEASE OPEN IT AS SOON AS U CAN ",null, null);
					piyu.sendTextMessage(phone3,null,"An urgent e-mail has been sent to your id\n PLEASE OPEN IT AS SOON AS U CAN ",null, null);
					Toast.makeText(getApplicationContext(), "SMS SENT",Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS not sent",Toast.LENGTH_LONG).show();
				}
*/
			/*	Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{mail2});
				email.putExtra(Intent.EXTRA_SUBJECT,"HIGHLY IMPORTANT");
				email.putExtra(Intent.EXTRA_TEXT," I'm travelling alone and feeling of lack of safety,i have sent this pic that might help  ");
				email.putExtra(Intent.EXTRA_STREAM, tempUri);
				//need this to prompts email client only
				email.setType("image/jpeg");
				try {
					startActivity(Intent.createChooser(email, "Send Mail"));
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "MAIL NOT SEND",Toast.LENGTH_LONG).show();

				}*/
			}
		});
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent7 =new Intent(Safe_m.this,Precaution.class);
				startActivity(intent7);
			}
		});
		btn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intt=new Intent(Safe_m.this,Loc.class);
				startActivity(intt);
						}
		});
		btn6.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
Intent intt=new Intent(Safe_m.this,call.class);
				startActivity(intt);
			}

		});


	}
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			if (requestCode == 200) {
				Bitmap photo = (Bitmap) data.getExtras().get("data");

					tempUri = getImageUri(getApplicationContext(), photo);
					// CALL THIS METHOD TO GET THE ACTUAL PATH
				String mail1=sp.getString("To",null);
					Intent email = new Intent(Intent.ACTION_SEND);
					email.putExtra(Intent.EXTRA_EMAIL, new String[]{mail1});
					email.putExtra(Intent.EXTRA_SUBJECT, "HIGHLY IMPORTANT");
					email.putExtra(Intent.EXTRA_TEXT, " I'm travelling alone and feeling of lack of safety,I have sent this pic that might help  ");
					email.putExtra(Intent.EXTRA_STREAM, tempUri);
					//need this to prompts email client only
					email.setType("image/jpeg");
					try {
						startActivity(Intent.createChooser(email, "Send Mail"));
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), "MAIL NOT SEND", Toast.LENGTH_LONG).show();

					}
				}
		}
		catch(Exception e){
			Toast.makeText(Safe_m.this,"Please Click An Image",Toast.LENGTH_LONG).show();

		}
		};
		 public Uri getImageUri(Context inContext, Bitmap inImage) {
			    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
			    String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
			    return Uri.parse(path);
			}

	}
