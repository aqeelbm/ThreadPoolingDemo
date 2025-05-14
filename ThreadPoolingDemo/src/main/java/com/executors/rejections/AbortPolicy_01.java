package com.executors.rejections;

import java.util.concurrent.*;
import java.util.concurrent.TimeUnit.*;
import java.time.*;

/**
 * @author Aqeel
 */
public class AbortPolicy_01 {
	public static void main(String[] args) {
		int corePoolSize = 1;
		int maxPoolSize = 5;
		long keepAliveTime = 0L;
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy(); 


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
						System.out.println("task:"+cnt+" "+Thread.currentThread().getName());
						Thread.sleep(1000);
					}
					catch(InterruptedException ex ) {
						ex.printStackTrace();
					}
				});
			}
		} finally {

			System.out.println("----------");
			System.out.println(exec.getRejectedExecutionHandler());
			System.out.println("finally .. ");
			exec.shutdown();	
		}

	}
}
