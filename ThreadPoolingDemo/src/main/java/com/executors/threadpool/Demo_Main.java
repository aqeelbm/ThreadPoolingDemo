/**
 * 
 */
package com.executors.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class Demo_Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = 
				new ThreadPoolExecutor(1, 
										1,
										0L, 
										TimeUnit.MILLISECONDS,
										new LinkedBlockingQueue<Runnable>());
		
		System.out.println("Queue: "+poolExecutor.getQueue());
		
		try {
			for (int i=0;i<5;i++) {
				final int cnt =i+1;
				poolExecutor.execute(() -> {
					try{
						 System.out.println("max pool size: "+poolExecutor.getMaximumPoolSize());
						 System.out.println("core pool size: "+poolExecutor.getCorePoolSize());
						 System.out.println("task count: "+poolExecutor.getTaskCount());
						 System.out.println("completed tasks: "+poolExecutor.getCompletedTaskCount());
						System.out.println("task:"+cnt+" "+Thread.currentThread().getName());
						System.out.println();
						Thread.sleep(2000);
					}
					catch(InterruptedException ex ) {
						ex.printStackTrace();
					}
				});
			}
		} finally {
			System.out.println("finally .. ");
			poolExecutor.shutdown();	
		}
	}

}
