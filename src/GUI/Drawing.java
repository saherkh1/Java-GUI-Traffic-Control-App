package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Drawing extends Canvas {
	/*
	int x1, y1, x2, y2, d, h;
	
	
	public void setValues(int x1, int y1, int x2, int y2, int d, int h){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.d = d;
		this.h = h;
	}
	
	public void paint(Graphics g) { 
		 System.out.println("the car was added");
	    int dx = x2 - x1, dy = y2 - y1, delta = 10;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = delta, xn = xm, ym = h, yn = -h, x;
	    double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx; 
	    double sin = dy / D, cos = dx / D;
	    x = xm*cos - ym*sin + x1;
	    xx = xm1*cos - ym1*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    ym1 = xm1*sin + ym1*cos + y1;
	    xm = x;
	    xm1 = xx; 
	    x = xn*cos - yn*sin + x1;
	    xx = xn1*cos - yn1*sin + x1; 
	    yn = xn*sin + yn*cos + y1;
	    yn1 = xn1*sin + yn1*cos + y1;
	    xn = x;
	    xn1 = xx;
	    int[] xpoints = {(int) xm1, (int) xn1,  (int) xn, (int) xm};
	    int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
	    g.fillPolygon(xpoints, ypoints, 4);
	    g.setColor(Color.BLACK);
	    g.fillOval((int) xm1-2,(int) ym1-2,4,4);
	    g.fillOval((int) xn1-2,(int) yn1-2,4,4);
	    g.fillOval((int) xm-2,(int) ym-2,4,4);
	    g.fillOval((int) xn-2,(int) yn-2,4,4);
	    
	}*/

}
