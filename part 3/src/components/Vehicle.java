/**
 * 
 */
package components;


import java.util.ArrayList;

//import org.graalvm.compiler.lir.LIRInstruction.Alive;

import GUI.RoadFrame;
import GUI.RoadMapPanel;
import utilities.Point;
import utilities.Timer;
import utilities.Utilities;
import utilities.VehicleType;
/**
 * @author Sophie Krimberg
 *
 */
/**
 * @author krsof
 *
 */
public class Vehicle implements Utilities, Timer,Runnable {
	private int id;
	private VehicleType vehicleType;
	private Route currentRoute;
	private RouteParts currentRoutePart;
	private int timeFromRouteStart;
	private static int objectsCount=1;
	private int timeOnCurrentPart;
	private Road lastRoad;
	private String status;
	private volatile int currentX;
	private volatile int currentY;
	private RoadFrame roadFrame;
	private Thread Vthread;
	volatile double  Vx = 0 ;
	volatile double Vy = 0 ;
	double TimeToArrive;
	
	
	boolean finishedcrossline = false;
	
	int speed;
	int  dx ,dy;
	double D ;
	double sin, cos ;
	public volatile boolean active=true;
	
	
	@Override
	public void incrementDrivingTime() {
		timeFromRouteStart++;
		timeOnCurrentPart++;
		/*
		if(TimeToArrive >=  1.0) {		
		TimeToArrive--;
		}
		else {
			try {
				Vthread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		/*
		if(TimeToArrive == 0.0) {
			System.out.println("Got to finish of the junction");
		}*/
		TimeToArrive--;
		
		move();
	}
	
	/**controls the vehicle moving from one route part to the next one
	 * 
	 */

	public void move() {
		/*
		if (currentRoutePart.canLeave(this)) {
			currentRoutePart.checkOut(this);
			currentRoute.findNextPart(this).checkIn(this);
		}
		else {*/
			currentRoutePart.stayOnCurrentPart(this);
			setCurrentY((int) ((int)getCurrentY()+Vy));
			setCurrentX((int) ((int)getCurrentX()+Vx));
			//System.out.println(timeFromRouteStart+"  "+TimeToArrive);

		//}
			
			
		
	}
	public void calcLocation() {
		double x1 = lastRoad.getStartJunction().getX();
		double y1 = lastRoad.getStartJunction().getY();
		double x2 = lastRoad.getEndJunction().getX();
		double y2 = lastRoad.getEndJunction().getY();
		speed=getVehicleType().getAverageSpeed();
		dx = (int) (x2 - x1);
		dy = (int) (y2 - y1);
		D = Math.sqrt(dx*dx + dy*dy);
		sin = dy / D;
		cos = dx / D;
		
		Vx=(int) ( (((speed/10)*cos))); 
		Vy= (int) ( (((speed/10)*sin)));
		double x = lastRoad.getEndJunction().getX()-currentX;
		double y = lastRoad.getEndJunction().getY()-currentY;
		double dist = Math.sqrt(dx*dx + dy*dy);
		double dspeed=Math.sqrt(Vx*Vx + Vy*Vy);
		TimeToArrive=dist/dspeed;
		//TimeToArrive=dist/dspeed;
		//System.out.println(TimeToArrive);
		/*
		double x1 = ((Road) currentRoutePart).getStartJunction().getX();
		double y1 = ((Road) currentRoutePart).getStartJunction().getY();
		double x2 = ((Road) currentRoutePart).getEndJunction().getX();
		double y2 = ((Road) currentRoutePart).getEndJunction().getY();
		int dx=(int) (x2-x1),dy=(int) (y2-y1);
		double a = dy/dx ;
		a = Math.toDegrees(Math.atan(a));
		if (a < 0)
		    a = 180 + a;

		Vx = vehicleType.getAverageSpeed() / 10 * Math.cos(Math.toRadians(a));
		Vy = vehicleType.getAverageSpeed() / 10 * Math.sin(Math.toRadians(a));
		Vx = Math.abs(Vx);
		Vy = Math.abs(Vy);
		if (x2 <= x1)
		    Vx *= -1;
		if (y2 <= y1)
		    Vy *= -1;
		 TimeToArrive = Math.abs((x2 - x1) / Vx);*/
	}
	
	@Override
	public String toString() {
		return new String("Vehicle "+id+": "+ getVehicleType().name()+", average speed: "+getVehicleType().getAverageSpeed());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false; 
	    if (getClass() != obj.getClass()) return false; 
	    if (! super.equals(obj)) return false;
		Vehicle other=(Vehicle)obj;
		if (this.currentRoute!=other.currentRoute||
			this.currentRoutePart!=other.currentRoutePart||
			this.id!=other.id||
			this.lastRoad!=other.lastRoad||
			this.status!=other.status||
			this.timeFromRouteStart!=other.timeFromRouteStart||
			this.timeOnCurrentPart!=other.timeOnCurrentPart||
			this.vehicleType!=other.vehicleType)
				return false;
		return true;
	}

	/**
	 * @param objectsCount the objectsCount to set
	 */
	public static void setObjectsCount(int objectsCount) {
		Vehicle.objectsCount = objectsCount;
	}




	@Override
	public void run() {
		//calcLocation();
		while (RoadMapPanel.isStartObs() && active) {
			double x = lastRoad.getEndJunction().getX()-currentX;
			double y = lastRoad.getEndJunction().getY()-currentY;
			double dist = Math.sqrt(x*x + y*y);
			double dspeed=12;
			if((dist < dspeed)/*Math.abs(TimeToArrive)==Math.abs(timeOnCurrentPart)*/ ) {
				//if(TimeToArrive >= 0.0) {
				//currentY=(int) lastRoad.endJunction.getY();
				//currentX=(int) lastRoad.endJunction.getX();
				if(active)
					active=false;
					/*try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					
				
			}else
            try { 
            	
    			incrementDrivingTime();
                   Thread.sleep(100);
            } catch (InterruptedException ex) {
                   ex.printStackTrace();
            }
		}
	}
	


	/**Random Constructor
	 * @param currentLocation
	 */
	public Vehicle (Road currentLocation) {

		id=objectsCount++;
		vehicleType=currentLocation.getVehicleTypes()[getRandomInt(0,currentLocation.getVehicleTypes().length-1)];
		System.out.println();
		successMessage(this.toString());
		currentRoute=new Route(currentLocation, this); //creates a new route for the vehicle and checks it in
		lastRoad=currentLocation;
		status=null;
		currentX= (int) lastRoad.getStartJunction().getX();
		currentY=(int) lastRoad.getStartJunction().getY();
		
		
	}
	public Vehicle (Road currentLocation,RoadFrame roadframe) {

		id=objectsCount++;
		vehicleType=currentLocation.getVehicleTypes()[getRandomInt(0,currentLocation.getVehicleTypes().length-1)];
		System.out.println();
		successMessage(this.toString());
		currentRoute=new Route(currentLocation, this); //creates a new route for the vehicle and checks it in
		lastRoad=currentLocation;
		status=null;
		this.roadFrame=roadframe;
		this.Vthread=new Thread(this);
		currentX= (int) lastRoad.getStartJunction().getX();
		currentY=(int) lastRoad.getStartJunction().getY();
		
	}
	

	public double getVx() {
		return Vx;
	}
	public void setVx(double vx) {
		Vx = vx;
	}
	public double getVy() {
		return Vy;
	}
	public void setVy(double vy) {
		Vy = vy;
	}
	public int getCurrentX() {
		return currentX;
	}
	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}
	public int getCurrentY() {
		return currentY;
	}
	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}

	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	/**
	 * @return the currentRoute
	 */
	public Route getCurrentRoute() {
		return currentRoute;
	}
	/**
	 * @param currentRoute the currentRoute to set
	 */
	public void setCurrentRoute(Route currentRoute) {
		this.currentRoute = currentRoute;
	}




	/**
	 * @return the currentRoutePart
	 */
	public RouteParts getCurrentRoutePart() {
		return currentRoutePart;
	}




	/**
	 * @param currentRoutePart the currentRoutePart to set
	 */
	public void setCurrentRoutePart(RouteParts currentRoutePart) {
		this.currentRoutePart = currentRoutePart;
	}




	/**
	 * @return the timeFromRouteStart
	 */
	public int getTimeFromRouteStart() {
		return timeFromRouteStart;
	}




	/**
	 * @param timeFromRouteStart the timeFromRouteStart to set
	 */
	public void setTimeFromRouteStart(int timeFromRouteStart) {
		this.timeFromRouteStart = timeFromRouteStart;
	}




	/**
	 * @return the timeOnCurrentPart
	 */
	public int getTimeOnCurrentPart() {
		return timeOnCurrentPart;
	}




	/**
	 * @param timeOnCurrentPart the timeOnCurrentPart to set
	 */
	public void setTimeOnCurrentPart(int timeOnCurrentPart) {
		this.timeOnCurrentPart = timeOnCurrentPart;
	}




	/**
	 * @return the lastRoad
	 */
	public Road getLastRoad() {
		return lastRoad;
	}




	/**
	 * @param lastRoad the lastRoad to set
	 */
	public void setLastRoad(Road lastRoad) {
		this.lastRoad = lastRoad;
	}




	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}




	/**
	 * @return the objectsCount
	 */
	public static int getObjectsCount() {
		return objectsCount;
	}




	
}
