/**
 * 
 */
package com.executors.scheduled;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class Schedule_01 {
	 public static void main(String[] args) 
	    { 
		 
	        // Creating a ScheduledThreadPoolExecutor object 
	        ScheduledThreadPoolExecutor threadPool 
	            = new ScheduledThreadPoolExecutor(2); 
	  
	        // Creating two Runnable objects 
	        Runnable task1 = new Command("task-1"); 
	        Runnable task2 = new Command("task-2"); 
	  
	        // Printing the current time in seconds 
	        System.out.println( 
	            "Current time : "
	            + Calendar.getInstance().get(Calendar.HOUR) + "." +
	            + Calendar.getInstance().get(Calendar.MINUTE) + "." +
	            + Calendar.getInstance().get(Calendar.SECOND)); 
	  
	        // Scheduling the first task which will execute 
	        // after 2 seconds 
	        threadPool.schedule(task1, 2, TimeUnit.SECONDS); 
	  
	        // Scheduling the second task which will execute 
	        // after 5 seconds 
	        threadPool.schedule(task2, 5, TimeUnit.SECONDS); 
	  
	        // Remember to shut sown the Thread Pool 
	        threadPool.shutdown(); 
	    } 
}
