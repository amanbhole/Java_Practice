package com.demo.threadpool;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask implements Runnable {

	private Timer mTimer;
	
	public MyTimerTask(Timer mTimer) {
		this.mTimer = mTimer;
	}
	
	@Override
	public void run() {
		synchronized (mTimer) {
			
		}
	}

	@Override
	public boolean cancel() {
		// TODO Auto-generated method stub
		return super.cancel();
	}
	
}