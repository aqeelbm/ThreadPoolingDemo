/**
 * 
 */
package com.executors.scheduled;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Aqeel
 */
public class ScheduleWithFixedDelay {
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
	  
	  
	        // Scheduling the second task which will execute 
	        // after 0 seconds and then there will be a delay of 
	        // 3 seconds between the completion 
	        // of one execution and the commencement of the next 
	        // execution 
	        threadPool.scheduleWithFixedDelay(task2, 0, 3, 
	                                          TimeUnit.SECONDS); 
	  
	        // Wait for 30 min 
	        try { 
	            Thread.sleep(300000); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	        
	        // Remember to shut sown the Thread Pool 
	        threadPool.shutdown(); 
	    } 
}
