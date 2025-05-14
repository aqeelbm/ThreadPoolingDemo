/**
 * 
 */
package com.executors.threadpool;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class ThreadPool_InvokeAny {
	public static void main(String[] arg) {


		Callable<String> tasks = new Callable<String>() {
			@Override
			public String call() throws Exception {

				System.out.println("inside call() - " 
						+ LocalTime.now().getHour()+":"
						+LocalTime.now().getMinute()+":"
						+LocalTime.now().getSecond());

				Thread.sleep(3000);
				return "hello from call().";
			}
		};

		ThreadPoolExecutor poolExecutor = 
				new ThreadPoolExecutor(1, 
						1,
						0L, 
						TimeUnit.MILLISECONDS,
						new LinkedBlockingQueue<Runnable>());



		try {
			String result = poolExecutor.invokeAny(List.of(tasks, tasks, tasks));

			System.out.println(result);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if(poolExecutor != null) {
				poolExecutor.shutdown();
			}
		}


	}
}
