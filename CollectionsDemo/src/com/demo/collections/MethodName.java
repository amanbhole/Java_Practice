package com.demo.collections;

public class MethodName {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0; i<4;i++) {
			String s1 = m1(i);

			System.out.println(s1);
		}
	}

	public static String m1(int i) {
		return getMethodName(i);
	}
	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		//System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
		// return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
		// return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
		return name;
	}

}
