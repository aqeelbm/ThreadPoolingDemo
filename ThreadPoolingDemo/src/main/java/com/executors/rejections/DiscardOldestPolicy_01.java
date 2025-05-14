/**
 * 
 */
package com.executors.rejections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class DiscardOldestPolicy_01 {

	public static void main(String[] args) {
		int corePoolSize = 1;
		int maxPoolSize = 5;
		long keepAliveTime = 0L;
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy(); 


		ThreadPoolExecutor exec = new ThreadPoolExecutor(
				corePoolSize,
				maxPoolSize, // 5
				keepAliveTime,
				TimeUnit.SECONDS, 
				queue, // 5
				handler);

		try {
			for (int i=0;i<15;i++) {
				final int cnt =i+1;
				exec.execute(() -> {
					try{
						System.out.println("task:"+cnt+"  "+Thread.currentThread().getName());
						Thread.sleep(1000);
					}
					catch(InterruptedException ex ) {
						System.out.println(ex);
					}
				});
			}
		} catch(Exception ex) {
			System.out.println(ex);
		} 
		finally {
			System.out.println("finally .. ");
			exec.shutdown();	
		}
	}
}
