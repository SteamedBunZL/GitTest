package com.rising.activity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import com.rising.gittest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CountDownLatchAndCyclicBarrierActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_countdownlatchandcyclcbarrier);
		// main();
		main2();
	}

	public class Work implements Runnable {
		private int id;
		private CountDownLatch beginSignal;
		private CountDownLatch endSignal;

		public Work(int id, CountDownLatch begin, CountDownLatch end) {
			this.id = id;
			this.beginSignal = begin;
			this.endSignal = end;
		}

		@Override
		public void run() {
			try {
				beginSignal.await();
				Log.i("", "===GIT ���ܡ�����");
				Log.i("", "===GIT work " + id + "�����յ�");
				endSignal.countDown();
				Log.i("", "===GIT work " + id + "��������������");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void main() {
		CountDownLatch beginSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(8);

		for (int i = 0; i < 8; i++) {
			new Thread(new Work(i, beginSignal, endSignal)).start();
		}

		try {
			beginSignal.countDown();// ͳһ����
			endSignal.await();// �ȴ��˶�Ա�����յ�
			Log.e("", "===GIT ������͵��㱨�ɼ���ϵͳ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public class Player implements Runnable {

		private CyclicBarrier cyclicBarrier;
		private int id;

		public Player(int id, CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				Log.i("", "===GIT ��� " + id + "�������һ�ء�����");
				cyclicBarrier.await();
				Log.i("", "===GIT ��� " + id + "����ڶ��ء�����");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

	}

	public void main2() {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {

			@Override
			public void run() {
				Log.e("", "===GIT ������ҽ���ڶ���!");
			}
		});

		for (int i = 0; i < 4; i++) {
			new Thread(new Player(i, cyclicBarrier)).start();
		}
	}

}
