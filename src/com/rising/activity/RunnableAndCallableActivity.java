package com.rising.activity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.rising.gittest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class RunnableAndCallableActivity extends Activity {

	private ExecutorService mExecutor = Executors.newSingleThreadExecutor();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runnableandcallable);
		runnableDemo();
		futureDemo();
	}

	/**
	 * Ч�ʵ��µ�쳲��������У���ʱ����
	 * 
	 * @param num
	 * @return
	 */
	int fibc(int num) {
		if (num == 0)
			return 0;
		if (num == 1)
			return 1;
		return fibc(num - 1) + fibc(num - 2);
	}

	/**
	 * runnalbe,�޷��ؽ��
	 */
	void runnableDemo() {
		new Thread() {
			@Override
			public void run() {
				Log.i("", "===GIT runnable demo : " + fibc(20));
			}
		}.start();
	}

	void futureDemo() {
		try {
			// �ύrunnable û�з���ֵ
			Future<?> result = mExecutor.submit(new Runnable() {

				@Override
				public void run() {
					fibc(20);
				}
			});

			Log.i("", "===GIT future result from runnable : " + result.get());

			// �ύ callable �з���ֵ
			Future<Integer> result2 = mExecutor.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return fibc(20);
				}
			});
			Log.i("", "===GIT future result from callable : " + result2.get());

			FutureTask<Integer> futureTask = new FutureTask<Integer>(
					new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							return fibc(20);
						}
					});
			mExecutor.submit(futureTask);
			Log.i("",
					"===GIT future result from futureTask : "
							+ futureTask.get());

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
