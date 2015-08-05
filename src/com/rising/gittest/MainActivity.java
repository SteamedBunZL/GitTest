package com.rising.gittest;

import com.rising.activity.CountDownLatchAndCyclicBarrierActivity;
import com.rising.activity.DisplayMetricsDpiActivity;
import com.rising.activity.RoundViewActivity;
import com.rising.activity.RunnableAndCallableActivity;
import com.rising.utils.ContentValue;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mListView;
	private ArrayAdapter<String> mItemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listview);
		mItemAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ContentValue.mainItem);
		mListView.setAdapter(mItemAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = null;
		switch (position) {
		case 0:// DisplayMetricsDpi
			startIntent(DisplayMetricsDpiActivity.class);
			break;
		case 1:// RoundView
			startIntent(RoundViewActivity.class);
			break;
		case 2:// Runnable¡¢Callable¡¢Future¡¢FutureTaskµÄÇø±ð
			startIntent(RunnableAndCallableActivity.class);
			break;
		case 3:
			startIntent(CountDownLatchAndCyclicBarrierActivity.class);
			break;

		}
	}

	private void startIntent(Class class1) {
		Intent intent = new Intent(MainActivity.this, class1);
		startActivity(intent);
	}

}
