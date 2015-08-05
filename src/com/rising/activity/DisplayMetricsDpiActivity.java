package com.rising.activity;

import com.rising.gittest.R;
import com.rising.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMetricsDpiActivity extends Activity {
	private TextView mTextDpi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displaymetricsdpi);
		mTextDpi = (TextView) findViewById(R.id.textdpi);
		mTextDpi.setText(Utils
				.getDisplayMetrics(DisplayMetricsDpiActivity.this));
	}
}
