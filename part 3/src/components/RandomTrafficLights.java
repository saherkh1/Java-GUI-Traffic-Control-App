/**
 * 
 */
package components;
import java.util.ArrayList;

import GUI.RoadMapPanel;

/**Represents the traffic lights with random choice of road that receives a green light
 * @author Sophie Krimberg
 *
 */
public class RandomTrafficLights extends TrafficLights {
	
	/**Constructor
	 * @param roads
	 */
	public RandomTrafficLights(ArrayList<Road> roads) {
		super(roads);
	}

	@Override
	public void changeIndex() {
		
		this.setGreenLightIndex((getRandomInt(1,200))%this.getRoads().size());
		
	}
	
	@Override
	public String toString() {
		return new String("Random "+super.toString());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	            try {
	                Thread.sleep(30);
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }
	            try {
	            	changeIndex();
	            }catch(Exception e){}
	}
	
	


}
