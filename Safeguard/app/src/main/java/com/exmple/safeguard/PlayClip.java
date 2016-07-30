package com.exmple.safeguard;



import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.exmple.besafe.R;


public class PlayClip extends Service{
	MediaPlayer myPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public void onCreate()
	{
		super.onCreate();
		//Toast.makeText(getApplicationContext(), "Service Start", Toast.LENGTH_LONG).show();
		myPlayer=MediaPlayer.create(this, R.raw.po);
		myPlayer.setLooping(false);
	}
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "Service Destroy", Toast.LENGTH_LONG).show();
		myPlayer.stop();
	}
public int onStartCommand(Intent intent, int flags, int startId){
	Toast.makeText(getApplicationContext(), "Song Start", Toast.LENGTH_SHORT).show();
	myPlayer.start();
	return super.onStartCommand(intent, flags, startId);
}   

}
