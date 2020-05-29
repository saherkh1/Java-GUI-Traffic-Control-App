package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import java.time.Instant;
import java.util.ArrayList;

import components.Junction;
import components.LightedJunction;
import components.Map;
import components.Road;
import components.TrafficLights;
import components.Vehicle;
import utilities.Point;

public class DrawingMap extends Canvas {

		int numberOfVehicles=0;
	 	private ArrayList<Vehicle> vehicles=null;
		Map map=null;
		int x1, y1, x2, y2, d=10, h=4,delta=10;
		private boolean vFlag;
		int[] xpoints;// = {(int) xm1, (int) xn1,  (int) xn, (int) xm};
	    int[] ypoints;
	    double xm , xn , ym, yn, xm1 , xn1 , ym1, yn1 ;
	    Color vColor= Color.BLACK;//(0,0,0);
		
		public void setMap(Map map){
			this.map=map;
		
		}
		public void setColor(Color c) {
			this.vColor=c;
		}
		public void changeBG(Color bg) {
			setBackground(bg);
			
		}
		
		public void setV(ArrayList<Vehicle> v){
			vehicles=v;
			
			this.vFlag = true;
			numberOfVehicles=v.size();
		}
		@Override
		public void paint(Graphics g) { 
			if(map!=null ) {
				
			 System.out.println("addJunctions");
			 System.out.println("");
			 System.out.println("");
			 
			 ArrayList<Road> roads= map.getRoads();
			 for (Road r : roads) {
				 if(r.getEnabled()){
					 g.setColor(Color.BLACK);
					 g.drawLine(
							 (int)r.getStartJunction().getX(),
							 (int)r.getStartJunction().getY(),
							 (int)r.getEndJunction().getX(),
							 (int)r.getEndJunction().getY());	
					 }
				 }
				 ArrayList<Junction> juncs=map.getJunctions();
				 for (Junction j : juncs) {
					 if (j instanceof LightedJunction) 
						 if(((LightedJunction) j).getLights().getTrafficLightsOn()) 
							 g.setColor(Color.RED);
						 else 
							 g.setColor(Color.GREEN);
					 else {
						 	x1=(int) j.getEnteringRoads().get(0).getEndJunction().getX();
							y1=(int) j.getEnteringRoads().get(0).getEndJunction().getY();
							x2=(int) j.getEnteringRoads().get(0).getStartJunction().getX();
							y2=(int) j.getEnteringRoads().get(0).getStartJunction().getY();
							delta=3;d=20;h=3;
							calcArrow(x1,y1,x2,y2);
							int[] xpoints = {(int) xm1, (int) xn1,  (int) (xn+xm)/2, (int) xm1};
						    int[] ypoints = {(int) ym1, (int) yn1, (int) (yn+ym)/2, (int) ym1};
						    g.setColor(Color.green);
							g.fillPolygon(xpoints, ypoints, 4);
							g.setColor(Color.BLACK);
					 }
					 g.fillOval((int) j.getX()-5,(int) j.getY()-5,10,10);
					 
				 }
				 
			}
			if(vFlag) {
				delta=10;d=10;h=4;
				for (Vehicle v : vehicles) {
				x1=(int) v.getLastRoad().getStartJunction().getX();
				y1=(int) v.getLastRoad().getStartJunction().getY();
				x2=(int) v.getLastRoad().getEndJunction().getX();
				y2=(int) v.getLastRoad().getEndJunction().getY();
				calcArrow(x1,y1,x2,y2);
			    int[] xpoints = {(int) xm1, (int) xn1,  (int) xn, (int) xm};
			    int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
			    g.setColor(vColor);
			    g.fillPolygon(xpoints, ypoints, 4);
			    g.setColor(Color.BLACK);
			    g.fillOval((int) xm1-2,(int) ym1-2,4,4);
			    g.fillOval((int) xn1-2,(int) yn1-2,4,4);
			    g.fillOval((int) xm-2,(int) ym-2,4,4);
			    g.fillOval((int) xn-2,(int) yn-2,4,4);


				}
			}
			}
			
			
			private void calcArrow(int x1,int y1,int x2,int y2) {
				int dx = x2 - x1, dy = y2 - y1;
			    double D = Math.sqrt(dx*dx + dy*dy);
			    double xm = delta, xn = xm, ym = h, yn = -h, x;
			    double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx; 
			    double sin = dy / D, cos = dx / D;
			    x =  xm*cos  - ym*sin  + x1;
			    xx = xm1*cos - ym1*sin + x1;
			    this.ym = xm*sin  + ym*cos  + y1;
			    this.ym1 =xm1*sin + ym1*cos + y1; this.xm = x;this.xm1 = xx;
			    x =  xn*cos  - yn*sin  + x1;
			    xx = xn1*cos - yn1*sin + x1; 
			    this.yn = xn*sin  + yn*cos  + y1;
			    this.yn1 =xn1*sin + yn1*cos + y1;
			    this.xn = x;
			    this.xn1 = xx;
			}
			
		

}
