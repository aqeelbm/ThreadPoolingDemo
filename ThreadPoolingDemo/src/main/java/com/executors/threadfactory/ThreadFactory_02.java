/**
 * 
 */
package com.executors.threadfactory;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class ThreadFactory_02 {

	public static void main(String[] args)throws InterruptedException, ExecutionException  {
		int corePoolSize = 1;
		int maxPoolSize = 3;
		long keepAliveTime = 0L;
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy(); 

		ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

		ThreadPoolExecutor exec = new ThreadPoolExecutor(
										corePoolSize,
										maxPoolSize, // 3
										keepAliveTime,
										TimeUnit.SECONDS, 
										queue, // 3
										defaultThreadFactory,
										handler);

		try {
			for (int i=0;i<20;i++) {
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
