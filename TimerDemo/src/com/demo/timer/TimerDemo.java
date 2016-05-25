package com.demo.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {

	private Timer mTimer;
	private int count;
	
	public TimerDemo(int count) {
		this.count = count;
	}
	public TimerDemo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TimerDemo timerDemo = new TimerDemo(0);
		timerDemo.startTimer();
//		timerDemo.cancelTimer();
	}

	private void startTimer() {
		System.out.println("Starting Timer");
		if(mTimer == null){
			mTimer = new Timer();
			mTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					synchronized (mTimer) {
						if(count<=3) {
							System.out.println("Timer Running : "+count);
							count++;
							} else {
								pauseTimer();
								cancelTimer();
							}
					}
				}
			}, 0, 5000);
		}
	}

	private void pauseTimer() {
		System.out.println("Pausing Timer");
		try {
			mTimer.wait();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cancelTimer() {
		System.out.println("Cancelling Timer");
		mTimer.cancel();
	}
}
