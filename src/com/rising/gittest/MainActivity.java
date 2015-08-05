package com.rising.gittest;

import com.rising.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView mTextDpi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextDpi = (TextView) findViewById(R.id.textdpi);
		mTextDpi.setText(Utils.getDisplayMetrics(MainActivity.this));
	}

}
