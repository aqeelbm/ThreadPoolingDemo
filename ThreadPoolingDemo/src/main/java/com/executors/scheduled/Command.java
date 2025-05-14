/**
 * 
 */
package com.executors.scheduled;

import java.util.Calendar;

/**
 * @author Aqeel
 */
public class Command implements Runnable { 
    String taskName; 
    public Command(String taskName) 
    { 
        this.taskName = taskName; 
    } 
    public void run() 
    { 
        System.out.println( 
        		this.taskName + " Current time: "
            + Calendar.getInstance().get(Calendar.SECOND)); 
        
        delay();
    }
    
	/**
	 * 
	 */
	private void delay() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

}
