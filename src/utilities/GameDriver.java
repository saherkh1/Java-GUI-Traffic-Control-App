/**
 * 
 */
package utilities;

import components.Driving;

/**
 * @author Sophie Krimberg
 *
 */
public class GameDriver {

	
	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Driving driving=new Driving(10, 20);
		driving.drive(20);

		
	}
	

}
