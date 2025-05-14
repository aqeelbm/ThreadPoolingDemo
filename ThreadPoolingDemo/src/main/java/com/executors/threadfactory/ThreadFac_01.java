/**
 * 
 */
package com.executors.threadfactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class ThreadFac_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			int corePoolSize = 1;
			int maxPoolSize = 3;
			long keepAliveTime = 0L;
			BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
			RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy(); 
			
			// using the default implementation code from the Oracle code.
			ThreadFactory defaultThreadFactory = new DefaultThreadFactory();

			ThreadPoolExecutor exec = new ThreadPoolExecutor(
											corePoolSize,
											maxPoolSize, // 3
											keepAliveTime,
											TimeUnit.SECONDS, 
											queue, // 3
											defaultThreadFactory, 
											handler);

			try {
				for (int i=0;i<15;i++) {
					final int cnt =i+1;
					exec.execute(() -> {
						try{
							System.out.println("task:"+cnt+" "+Thread.currentThread().getName());
							Thread.sleep(500);
						}
						catch(InterruptedException ex ) {
							ex.printStackTrace();
						}
					});
				}
			} finally {
				System.out.println("finally .. ");
				exec.shutdown();	
			}
}

}
