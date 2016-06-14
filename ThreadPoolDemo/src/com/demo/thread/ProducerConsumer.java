package com.demo.thread;

//A correct implementation of a producer and consumer. 
class Q { 
	int n; 
	boolean valueSet = false; 
	synchronized int get() {
		if(!valueSet) 
			try { 
				wait(); 
			} catch(InterruptedException e) { 
				System.out.println("InterruptedException caught"); 
			} 
		System.out.println("Got: " + n + " " + Thread.currentThread().getName()); 
		valueSet = false; 
		notify(); 
		return n; 
	} 
	synchronized void put(int n) {
		if(valueSet) 
			try { 
				wait(); 
			} catch(InterruptedException e) { 
				System.out.println("InterruptedException caught"); 
			} 
		this.n = n; 
		valueSet = true; 
		System.out.println("Put: " + n + " " + Thread.currentThread().getName()); 
		notify(); 
	} 
}

class Producer implements Runnable { 
	Q q; 
	Producer(Q q) { 
		this.q = q; 
		new Thread(this, "Producer").start(); 
	} 
	public void run() { 
		int i = 0; 
		while(i<10) { 
			q.put(i++); 
		}
		System.exit(0);
	} 
}

class Consumer implements Runnable { 
	Q q; 
	Consumer(Q q) { 
		this.q = q; 
		new Thread(this, "Consumer").start(); 
	} 
	public void run() { 
		while(true) { 
			q.get(); 
		} 
	} 
}

public class ProducerConsumer { 
	public static void main(String args[]) { 
		System.out.println("Press Control-C to stop."); 
		Q q = new Q(); 
		new Producer(q); 
		new Consumer(q); 
	} 
}

