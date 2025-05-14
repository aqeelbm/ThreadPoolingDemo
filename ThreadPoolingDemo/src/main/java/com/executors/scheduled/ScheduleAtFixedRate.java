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
public class ScheduleAtFixedRate {
	 public static void main(String[] args) 
	    { 
	  
	        // Creating a ScheduledThreadPoolExecutor object 
	        ScheduledThreadPoolExecutor threadPool 
	            = new ScheduledThreadPoolExecutor(2); 
	  
	        // Creating two Runnable objects 
	        Runnable task1 = new Command("task-1"); 
	  
	        // Printing the current time in seconds 
	        System.out.println( 
		            "Current time : "
		    	            + Calendar.getInstance().get(Calendar.HOUR) + "." +
		    	            + Calendar.getInstance().get(Calendar.MINUTE) + "." +
		    	            + Calendar.getInstance().get(Calendar.SECOND)); 
	  
	        // Scheduling the first task which will execute 
	        // after 1 seconds and then repeats periodically with 
	        // a period of 3 seconds 
	        threadPool.scheduleAtFixedRate(task1, 1, 3, 
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
