package com.alexanderpope.alphabet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	static final String packageName = "com.alexanderpope.alphabet";
	static final int lettersCount = 26;
	static final int numbersCount = 10;
	static final int buttonsCount = lettersCount + numbersCount;
	
	static SoundPool soundPool	= new SoundPool(buttonsCount, AudioManager.STREAM_MUSIC, 0);
	static int[] soundIDs = new int[buttonsCount];
	
	static int[] imageResources = new int[buttonsCount];
	
	static Integer btnClickedTag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Context context = getApplicationContext();
		Resources resources = getResources();

		int numbersOffset = 25;
		int btn, audioResId;
		String audioResName, imageResName;

		for (btn = 0; btn < lettersCount; ++btn) {
			audioResName = String.format("%c", btn + 'a');
			audioResId = resources.getIdentifier(audioResName, "raw",
					packageName);
			
			soundIDs[btn] = soundPool.load(context, audioResId, 1);
			
			imageResName = String.format("ic_action_%c", btn + 'a');
			imageResources[btn] = resources.getIdentifier(imageResName,
					"drawable", packageName);
		}

		// app displays 1-10 btn starts with 1 and includes 10 btn is used to
		// generate names;
		// therefore requiring calculations in String.format() calls or in
		// resource arrays access;
		// I chose the latter using numbersOffset to continue filling resource
		// arrays from where left off;
		for (btn = 1; btn <= numbersCount; ++btn) {
			audioResName = String.format("a%02d", btn);
			audioResId = resources.getIdentifier(audioResName, "raw",
					packageName);
			
			soundIDs[numbersOffset + btn] = soundPool.load(context, audioResId, 1);

			imageResName = String.format("ic_action_%02d", btn);
			imageResources[numbersOffset + btn] = resources.getIdentifier(
					imageResName, "drawable", packageName);
		}

		View.OnClickListener onClickBtn = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				btnClickedTag = (Integer) v.getTag();
				soundPool.play(soundIDs[btnClickedTag], 1f, 1f, 1, 0, 1f);
			}
		};

		LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);
		LinearLayout row;
		int rowCount = 6;
		int itemCount = 6;
		int r, i, index;

		for (r = 0; r < rowCount; ++r) {
			row = new LinearLayout(this);
			row.setLayoutParams(new TableLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f));
			row.setOrientation(LinearLayout.HORIZONTAL);
			
			for (i = 0; i < itemCount; ++i) {
				index = itemCount * r + i;

				ImageView imageView = new ImageView(context);
				imageView.setTag(index);
				imageView.setLayoutParams(new TableLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,
						1f));
				imageView.setPadding(16,16,16,16);
				imageView.setImageResource(imageResources[index]);
				imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
				imageView.setOnClickListener(onClickBtn);

				row.addView(imageView);
			}

			mainLinearLayout.addView(row);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
