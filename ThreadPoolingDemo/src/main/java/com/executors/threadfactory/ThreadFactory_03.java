/**
 * 
 */
package com.executors.threadfactory;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aqeel
 */
public class ThreadFactory_03 {

	public static void main(String[] args) {
		int corePoolSize = 1;
		int maxPoolSize = 3;
		long keepAliveTime = 0L;
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy(); 

		ThreadFactory customThreadFactory = new ThreadFactory() {
			private final AtomicInteger threadNumber = new AtomicInteger(1);
			private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

			@Override
			public Thread newThread(Runnable r) {
				Thread thread = defaultFactory.newThread(r);
				thread.setName("CustomThread-" + threadNumber.getAndIncrement());
				thread.setDaemon(false);
				thread.setPriority(10);
				return thread;
			}
		};

		ThreadPoolExecutor exec = new ThreadPoolExecutor(
				corePoolSize,
				maxPoolSize, // 3
				keepAliveTime,
				TimeUnit.SECONDS, 
				queue, // 3
				customThreadFactory,
				handler);

		try {
			for (int i=0;i<15;i++) {
				final int cnt =i+1;
				exec.execute(() -> {
					try{
						System.out.println("task:"+cnt+" "
											+Thread.currentThread().getName()+" "
											+Thread.currentThread().getPriority()+" "
											+Thread.currentThread().getId()+" "
											+Thread.currentThread().getThreadGroup());
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
