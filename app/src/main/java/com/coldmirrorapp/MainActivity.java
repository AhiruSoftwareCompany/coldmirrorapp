package com.coldmirrorapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button stop = (Button) (findViewById(R.id.stop));
		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (mediaPlayer != null) {
						mediaPlayer.pause();
						mediaPlayer.release();
					}
					mediaPlayer = null;
				} catch (Exception e){

				}
			}
		});
	}

	public void onClick(View v) {
		try {
			int resId = getResources().getIdentifier(v.getTag() + "", "raw", getPackageName());
			mediaPlayer = MediaPlayer.create(this, resId);
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}

			});
			mediaPlayer.start();

		} catch (Exception e) {
			Toast.makeText(this, "Das geht nicht!", Toast.LENGTH_SHORT).show();
		}
	}
}
